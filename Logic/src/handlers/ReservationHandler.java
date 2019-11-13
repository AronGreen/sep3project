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
    public DataResponse<Reservation> create(Reservation reservation) {
        // No logic is implemented for now
        DataResponse<Reservation> res = service.create(reservation);

        return res;
    }

    @Override
    public DataResponse<Reservation> update(Reservation reservation) {
        // No logic is implemented for now
        DataResponse<Reservation> res = service.update(reservation);

        return res;
    }

    @Override
    public DataResponse<Reservation[]> getByTripId(int tripId) {
        // No logic is implemented for now
        DataResponse<Reservation[]> res = service.getByTripId(tripId);

        return res;
    }
}
