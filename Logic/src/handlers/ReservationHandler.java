package handlers;

import models.Reservation;
import services.DataResponse;

public class ReservationHandler implements IReservationHandler {
    @Override
    public DataResponse<Reservation> create(Reservation reservation) {
        return null;
    }

    @Override
    public DataResponse<Reservation> update(Reservation reservation) {
        return null;
    }

    @Override
    public DataResponse<Reservation[]> getByTripId(int tripId) {
        return null;
    }
}
