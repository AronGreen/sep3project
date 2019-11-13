package services;

import models.Reservation;


public class ReservationService implements IReservationService {

    DataConnection connection = DataConnection.INSTANCE;

    @Override
    public DataResponse<String> create(Reservation reservation) {
        // Construct Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "create", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response
        DataResponse<String> res = DataResponse.fromJson(json, String.class);

        return res;
    }

    @Override
    public DataResponse<String> update(Reservation reservation) {
        // Construct the Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "update", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response
        DataResponse<String> res = DataResponse.fromJson(json, String.class);

        return res;
    }

    @Override
    public DataResponse<String> delete(int id) {
        return null;
    }

    @Override
    public DataResponse<String> getById(int id) {
        return null;
    }

    @Override
    public DataResponse<String> getByTripId(int tripId) {
        // Construct Request based on method and tripId
        DataRequest req = new DataRequest("reservation", "getByTripId", tripId + "");

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response
        DataResponse<String> res = DataResponse.fromJson(json, String.class);

        return res;
    }

    @Override
    public DataResponse<String> getByUserId(int userId) {
        return null;
    }
}
