package handlers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import helpers.DateTimeHelper;
import models.*;
import models.response.InvoiceResponse;
import models.response.TripListResponse;
import models.response.TripResponse;
import serviceproviders.PaymentState;
import services.*;

import java.util.List;

public class TripHandler implements ITripHandler {

    private ITripService tripService;
    private IReservationService reservationService;
    private IAccountService accountService;
    private IInvoiceHandler invoiceHandler = new InvoiceHandler();
    private INotificationService notificationService = DependencyCollection.getNotificationService();
    private IInvoiceService invoiceService = DependencyCollection.getInvoiceService();

    public TripHandler() {
        tripService = new TripService();
        reservationService = new ReservationService();
    }

    @Override
    public TripResponse create(Trip trip) {
        // Validate number of seats
        if (trip.getTotalSeats() < 1) {
            return new TripResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }

        // Validate fees
        if (trip.getBasePrice() < 0 || trip.getCancellationFee() < 0 || trip.getPerKmPrice() < 0) {
            return new TripResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }

        // Validate time

        // Validate trip interval
        // Trip[] driverTrips = tripService.getFiltered(
        //         new TripFilter(trip.getDriverEmail(), "", "", ""))
        //         .getBody();
        // cannot be performed now

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
                    if (invoice.getState().equals(PaymentState.PAID.toString()))
                        amount += invoice.getAmount();
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
        // No business logic needed for now

        return tripService.getFiltered(filter);
    }

    @Override
    public TripResponse getById(int id) {
        // No business logic needed for now

        return tripService.getById(id);
    }

}
