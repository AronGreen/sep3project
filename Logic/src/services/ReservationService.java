package services;

import models.Reservation;

import javax.xml.crypto.Data;


public class ReservationService implements IReservationService {

    private DataConnection connection = DataConnection.INSTANCE;

    @Override
    public DataResponse<String> create(Reservation reservation) {
        // Construct Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "create", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> update(Reservation reservation) {
        // Construct the Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "update", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> delete(int id) {
        DataRequest request = new DataRequest("reservation", "delete", id + "");

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> getById(int id) {
        DataRequest request = new DataRequest("reservation", "getById", id + "");

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> getByTripId(int tripId) {
        // Construct Request based on method and tripId
        DataRequest req = new DataRequest("reservation", "getByTripId", tripId + "");

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> getByEmail(String email) {
        DataRequest request = new DataRequest("reservation", "getByEmail", email);

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

}
