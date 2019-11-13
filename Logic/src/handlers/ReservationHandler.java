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
        DataResponse<String> res = service.create(reservation);

        return res;
    }

    @Override
    public DataResponse<String> update(Reservation reservation) {
        // No logic is implemented for now
        DataResponse<String> res = service.update(reservation);

        return res;
    }

    @Override
    public DataResponse<String> getByTripId(int tripId) {
        // No logic is implemented for now
        DataResponse<String> res = service.getByTripId(tripId);

        return res;
    }
}
