package handlers;

import models.Reservation;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;

public interface IReservationHandler {

    ReservationResponse create(Reservation reservation);
    ReservationResponse update(Reservation reservation);
    ReservationResponse delete(int id);
    ReservationResponse getById(int id);
    ReservationListResponse getByTripId(int tripId);
    ReservationListResponse getByEmail(String email);

}
