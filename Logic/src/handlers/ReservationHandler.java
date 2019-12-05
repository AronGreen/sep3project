package handlers;

import models.Reservation;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;
import services.IReservationService;
import services.ITripService;
import services.ReservationService;

public class ReservationHandler implements IReservationHandler {

    private IReservationService service;

    public ReservationHandler() {
        service = new ReservationService();
    }

    @Override
    public ReservationResponse create(Reservation reservation) {
        // No logic is implemented for now

        return service.create(reservation);
    }

    @Override
    public ReservationResponse update(Reservation reservation) {
        // No logic is implemented for now

        return service.update(reservation);
    }

    @Override
    public ReservationResponse delete(int id) {
        return service.delete(id);
    }

    @Override
    public ReservationResponse getById(int id) {
        return service.getById(id);
    }

    @Override
    public ReservationListResponse getByTripId(int tripId) {
        // No logic is implemented for now

        return service.getByTripId(tripId);
    }

    @Override
    public ReservationListResponse getByEmail(String email) {
        return service.getByEmail(email);
    }
}
