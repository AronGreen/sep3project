package handlers;

import constants.ResponseStatus;
import models.Invoice;
import models.Reservation;
import models.ReservationState;
import models.Trip;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;
import models.response.TripResponse;
import services.*;

public class ReservationHandler implements IReservationHandler {

    private IReservationService reservationService;
    private IInvoiceHandler invoiceHandler = new InvoiceHandler();
    private ITripService tripService = new TripService();

    public ReservationHandler() {
        reservationService = new ReservationService();
    }

    @Override
    public ReservationResponse create(Reservation reservation) {
        // No logic is implemented for now

        return reservationService.create(reservation);
    }

    @Override
    public ReservationResponse update(Reservation reservation) {
        ReservationResponse res = reservationService.getById(reservation.getId());
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS))
        {
            return reservationService.update(reservation);
        }

        Reservation old = res.getBody();

        TripResponse tripRes = tripService.getById(reservation.getTripId());
        if (!tripRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reservationService.update(reservation);
        }
        Trip trip = tripRes.getBody();

        if (old.getState().equals(ReservationState.PENDING) && reservation.getState().equals(ReservationState.APPROVED)) {
            invoiceHandler.create(new Invoice(
                    reservation.getPassengerEmail(),
                    trip.getDriverEmail(),
                    "Reservation fee",
                    trip.getBasePrice()
                    // TODO EXTEND THIS lol
            ));
        }

        return reservationService.update(reservation);
    }

    @Override
    public ReservationResponse delete(int id) {
        ReservationResponse res = reservationService.getById(id);
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reservationService.delete(id);
        }
        Reservation reservation = res.getBody();

        TripResponse tripRes = tripService.getById(reservation.getTripId());
        if (!tripRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reservationService.delete(id);
        }
        Trip trip = tripRes.getBody();

        if (reservation.getState().equals(ReservationState.APPROVED)) {
            invoiceHandler.create(new Invoice(
                    reservation.getPassengerEmail(),
                    trip.getDriverEmail(),
                    "Reservation cancellation fee",
                    trip.getCancellationFee()
            ));
        }

        return reservationService.delete(id);
    }

    @Override
    public ReservationResponse getById(int id) {
        return reservationService.getById(id);
    }

    @Override
    public ReservationListResponse getByTripId(int tripId) {
        // No logic is implemented for now

        return reservationService.getByTripId(tripId);
    }

    @Override
    public ReservationListResponse getByEmail(String email) {
        return reservationService.getByEmail(email);
    }
}
