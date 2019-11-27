package services;

import models.Reservation;

public interface IReservationService {

    DataResponse create(Reservation reservation);
    DataResponse update(Reservation reservation);
    DataResponse delete(int id);
    DataResponse getById(int id);
    DataResponse getByTripId(int tripId);
    DataResponse getByEmail(String email);

}
