package handlers;

import models.Reservation;
import services.DataResponse;
import services.IReservationService;
import services.ReservationService;

public class ReservationHandler implements IReservationHandler {

    private IReservationService service;

    public ReservationHandler() {
        service = new ReservationService();
    }

    @Override
    public DataResponse<String> create(Reservation reservation) {
        // No logic is implemented for now

        return service.create(reservation);
    }

    @Override
    public DataResponse<String> update(Reservation reservation) {
        // No logic is implemented for now

        return service.update(reservation);
    }

    @Override
    public DataResponse<String> delete(int id) {
        return service.delete(id);
    }

    @Override
    public DataResponse<String> getById(int id) {
        return service.getById(id);
    }

    @Override
    public DataResponse<String> getByTripId(int tripId) {
        // No logic is implemented for now

        return service.getByTripId(tripId);
    }

    @Override
    public DataResponse<String> getByEmail(String email) {
        return service.getByEmail(email);
    }
}
