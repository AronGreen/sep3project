package services;

import models.Reservation;

public interface IReservationService {

    DataResponse<String> create(Reservation reservation);
    DataResponse<String> update(Reservation reservation);
    DataResponse<String> delete(int id);
    DataResponse<String> getById(int id);
    DataResponse<String> getByTripId(int tripId);
    DataResponse<String> getByEmail(String email);

}
