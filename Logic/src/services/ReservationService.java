package services;

import helpers.JsonConverter;
import models.Reservation;

import javax.xml.crypto.Data;


public class ReservationService implements IReservationService {

    private DataConnection connection = DataConnection.INSTANCE;

    @Override
    public DataResponse create(Reservation reservation) {
        // Construct Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "create", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        return JsonConverter.fromJson(json, DataResponse.class);
    }

    @Override
    public DataResponse update(Reservation reservation) {
        // Construct the Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "update", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        return JsonConverter.fromJson(json, DataResponse.class);
    }

    @Override
    public DataResponse delete(int id) {
        DataRequest request = new DataRequest("reservation", "delete", id + "");

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, DataResponse.class);
    }

    @Override
    public DataResponse getById(int id) {
        DataRequest request = new DataRequest("reservation", "getById", id + "");

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, DataResponse.class);
    }

    @Override
    public DataResponse getByTripId(int tripId) {
        // Construct Request based on method and tripId
        DataRequest req = new DataRequest("reservation", "getByTripId", tripId + "");

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response

        return JsonConverter.fromJson(json, DataResponse.class);
    }

    @Override
    public DataResponse getByEmail(String email) {
        DataRequest request = new DataRequest("reservation", "getByEmail", email);

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, DataResponse.class);
    }

}
