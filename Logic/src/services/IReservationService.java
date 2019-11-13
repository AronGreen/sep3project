package services;

import models.Reservation;

public interface IReservationService {

    DataResponse<Reservation> create(Reservation reservation);
    DataResponse<Reservation> update(Reservation reservation);
    DataResponse<Reservation> delete(int id);
    DataResponse<Reservation> getById(int id);
    DataResponse<Reservation[]> getByTripId(int tripId);
    DataResponse<Reservation[]> getByUserId(int userId);

}
