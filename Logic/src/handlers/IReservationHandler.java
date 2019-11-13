package handlers;

import models.Reservation;
import services.DataResponse;

public interface IReservationHandler {

    DataResponse<String> create(Reservation reservation);
    DataResponse<String> update(Reservation reservation);
    DataResponse<String> getByTripId(int tripId);

}
