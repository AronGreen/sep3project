package handlers;

import constants.ResponseStatus;
import models.*;
import models.response.TripListResponse;
import models.response.TripResponse;
import services.*;

import java.util.List;

public class TripHandler implements ITripHandler {

    private ITripService tripService;
    private IReservationService reservationService;
    private IAccountService accountService;
    private IInvoiceHandler invoiceHandler = new InvoiceHandler();

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

        // Pay out cancellation fee
        reservations.forEach(r -> {
            if (r.getState().equals(ReservationState.APPROVED)) {
                invoiceHandler.create(new Invoice(
                        trip.getDriverEmail(),
                        r.getPassengerEmail(),
                        "Trip cancellation fee",
                        trip.getCancellationFee()
                ));
            }
        });

        // Set reservation states to cancelled
        reservations.forEach(r -> {
            // TODO send notifications
            switch (r.getState()) {
                case ReservationState.PENDING:
                case ReservationState.APPROVED:
                    r.setState(ReservationState.CANCELLED);
                    break;
            }
            r = reservationService.update(r).getBody();
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
