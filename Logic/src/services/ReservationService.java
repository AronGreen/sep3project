package services;

import models.Reservation;

import javax.xml.crypto.Data;

public class ReservationService implements IReservationService {

    DataConnection connection = DataConnection.INSTANCE;

    @Override
    public DataResponse<Reservation> create(Reservation reservation) {
        // Construct Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "create", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response
        DataResponse<Reservation> res = DataResponse.fromJson(json, Reservation.class);

        return res;
    }

    @Override
    public DataResponse<Reservation> update(Reservation reservation) {
        // Construct the Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "update", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response
        DataResponse<Reservation> res = DataResponse.fromJson(json, Reservation.class);

        return res;
    }

    @Override
    public DataResponse<Reservation> delete(int id) {
        return null;
    }

    @Override
    public DataResponse<Reservation> getById(int id) {
        return null;
    }

    @Override
    public DataResponse<Reservation[]> getByTripId(int tripId) {
        // Construct Request based on method and tripId
        DataRequest req = new DataRequest("reservation", "getByTripId", tripId + "");

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response
        DataResponse<Reservation[]> res = DataResponse.fromJson(json, Reservation[].class);

        return res;
    }

    @Override
    public DataResponse<Reservation[]> getByUserId(int userId) {
        return null;
    }
}
