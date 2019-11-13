package handlers;

import models.Reservation;
import services.DataResponse;

public interface IReservationHandler {

    DataResponse<Reservation> create(Reservation reservation);
    DataResponse<Reservation> update(Reservation reservation);
    DataResponse<Reservation[]> getByTripId(int tripId);

}
