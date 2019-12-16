package handlers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import helpers.DateTimeHelper;
import helpers.StringHelper;
import models.*;
import models.response.*;
import serviceproviders.navigation.INavigationServiceProvider;
import serviceproviders.payment.PaymentState;
import services.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripHandler implements ITripHandler {

    private ITripService tripService;
    private IReservationService reservationService;
    private IInvoiceHandler invoiceHandler = DependencyCollection.getInvoiceHandler();
    private INotificationService notificationService = DependencyCollection.getNotificationService();
    private IInvoiceService invoiceService = DependencyCollection.getInvoiceService();
    private INavigationServiceProvider navigationServiceProvider = DependencyCollection.getNavigationServiceProvider();

    public TripHandler() {
        tripService = new TripService();
        reservationService = new ReservationService();
    }

    @Override
    public TripResponse create(Trip trip) {
        if (trip == null || isInvalid(trip)) {
            return new TripResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }

        // Validate time
        // Get current trips of the driver
        TripListResponse tripsResponse = tripService.getFiltered(new TripFilter(trip.getDriverEmail(), "", DateTimeHelper.getCurrentTime(), "", "", ""));
        if (!tripsResponse.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return new TripResponse(ResponseStatus.SOCKET_INTERNAL_ERROR, null);
        }
        List<Trip> currentTrips = tripsResponse.getBody();

        // Get all reservations for all current trips
        List<List<Reservation>> currentTripsReservations = new ArrayList<>();
        for (Trip t : currentTrips) {
            ReservationListResponse reservationResponse = reservationService.getByTripId(t.getId());
            if (!reservationResponse.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
                return new TripResponse(ResponseStatus.SOCKET_INTERNAL_ERROR, null);
            }
            currentTripsReservations.add(reservationResponse.getBody());
        }

        // Get the details to know the start and end time
        TripDetails details = navigationServiceProvider.getTripDetails(trip, new ArrayList<>());
        List<TripDetails> tripDetailsList = navigationServiceProvider.getAllTripDetails(currentTrips, currentTripsReservations);

        // Check for overlaps
        for (TripDetails tripDetails : tripDetailsList) {
            if (DateTimeHelper.overlaps(details.getStartTime(), details.getEndTime(), tripDetails.getStartTime(), tripDetails.getEndTime()))
                return new TripResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }


        return tripService.create(trip);
    }

    @Override
    public TripResponse delete(int id) {
        // Get all reservations
        List<Reservation> reservations = reservationService.getByTripId(id).getBody();
        TripResponse res = tripService.getById(id);
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS))
            return res;

        Trip trip = res.getBody();

        // Pay out cancellation fee + paid reservations, and revoke unpaid reservations
        reservations.forEach(r -> {
            if (r.getState().equals(ReservationState.APPROVED)) {
                double amount = trip.getCancellationFee();
                InvoiceResponse invoiceRes = invoiceService.getByReservationId(r.getId());
                if (invoiceRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
                    Invoice invoice = invoiceRes.getBody();
                    if (invoice.getState().equals(PaymentState.PAID.toString())) {
                        amount += invoice.getAmount();
                        invoiceHandler.revoke(invoice.getId());
                    }
                    if (invoice.getState().equals(PaymentState.PENDING.toString())){
                        invoiceHandler.revoke(invoice.getId());
                    }
                }

                invoiceHandler.create(new Invoice(
                        trip.getId(),
                        r.getId(),
                        trip.getDriverEmail(),
                        r.getPassengerEmail(),
                        "Trip cancellation fee",
                        amount
                ));
            }
        });

        // Set reservation states to cancelled and send notifications
        reservations.forEach(r -> {
            switch (r.getState()) {
                case ReservationState.PENDING:
                case ReservationState.APPROVED:
                    r.setState(ReservationState.CANCELLED);
                    notificationService.create(new Notification(
                            r.getPassengerEmail(),
                            NotificationType.TRIP_CANCELLED.getEntityType(),
                            r.getId(),
                            NotificationType.TRIP_CANCELLED.getMessage(),
                            DateTimeHelper.getCurrentTime()
                    ));
                    break;
            }

            reservationService.update(r);
        });

        return tripService.delete(id);
    }

    @Override
    public TripListResponse getFiltered(TripFilter filter) {
        TripListResponse response = tripService.getFiltered(filter);

        // Handle server error
        if (!response.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return response;
        }
        List<Trip> trips = response.getBody();

        // Apply navigation filter
        if (!StringHelper.isNullOrEmpty(filter.getPickupAddress()) &&
                !StringHelper.isNullOrEmpty(filter.getDropoffAddress()))
        {
            for (int i = 0; i < trips.size(); i++) {
                if (getAvailabeSeats(trips.get(i)) <= 0)
                {
                    trips.remove(i);
                    i--;
                }
            }
            trips = navigationServiceProvider.getTripsForReservation(trips, filter.getPickupAddress(), filter.getDropoffAddress());
        }

        return new TripListResponse(response.getStatus(), trips);
    }

    @Override
    public TripResponse getById(int id) {
        return tripService.getById(id);
    }

    @Override
    public TripDetailsResponse getTripDetails(int id) {
        TripResponse tripResponse = tripService.getById(id);
        // Handle if trip does not exist
        if (!tripResponse.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return new TripDetailsResponse(tripResponse.getStatus(), null);
        }
        Trip trip = tripResponse.getBody();

        ReservationListResponse reservationResponse = reservationService.getByTripId(id);
        // Handle server error
        if (!reservationResponse.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return new TripDetailsResponse(reservationResponse.getStatus(), null);
        }
        List<Reservation> reservations = reservationResponse.getBody();

        TripDetails details = navigationServiceProvider.getTripDetails(trip, reservations);

        return new TripDetailsResponse(ResponseStatus.SOCKET_SUCCESS, details);
    }

    private int getAvailabeSeats(Trip trip) {
        int seats = trip.getTotalSeats();
        List<Reservation> reservations = reservationService.getByTripId(trip.getId()).getBody();

        for (Reservation res: reservations) {
            if (!res.getState().equals(ReservationState.REJECTED) && !res.getState().equals(ReservationState.CANCELLED))
                seats -= res.getBookedSeats();
        }

        return seats;
    }

    private boolean isInvalid(Trip trip) {
        return StringHelper.isNullOrEmpty(trip.getDriverEmail()) ||
                StringHelper.isNullOrEmpty(trip.getArrival()) ||
                StringHelper.isNullOrEmpty(trip.getStartAddress()) ||
                StringHelper.isNullOrEmpty(trip.getDestinationAddress()) ||
                trip.getBasePrice() < 0 ||
                trip.getPerKmPrice() < 0 ||
                trip.getCancellationFee() < 0 ||
                trip.getTotalSeats() <= 0 ||
                DateTimeHelper.fromString(trip.getArrival()).isBefore(LocalDateTime.now());
    }

}
