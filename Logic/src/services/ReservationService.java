package services;

import helpers.JsonConverter;
import models.Reservation;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;


public class ReservationService implements IReservationService {

    private DataConnection connection = DataConnection.INSTANCE;

    @Override
    public ReservationResponse create(Reservation reservation) {
        // Construct Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "create", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        return JsonConverter.fromJson(json, ReservationResponse.class);
    }

    @Override
    public ReservationResponse update(Reservation reservation) {
        // Construct the Request based on method and Reservation
        DataRequest req = new DataRequest("reservation", "update", reservation.toJson());

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        return JsonConverter.fromJson(json, ReservationResponse.class);
    }

    @Override
    public ReservationResponse delete(int id) {
        DataRequest request = new DataRequest("reservation", "delete", id + "");

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, ReservationResponse.class);
    }

    @Override
    public ReservationResponse getById(int id) {
        DataRequest request = new DataRequest("reservation", "getById", id + "");

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, ReservationResponse.class);
    }

    @Override
    public ReservationListResponse getByTripId(int tripId) {
        // Construct Request based on method and tripId
        DataRequest req = new DataRequest("reservation", "getByTripId", tripId + "");

        // Send Request and receive response json
        String json = connection.sendRequest(req);

        // Construct Response

        return JsonConverter.fromJson(json, ReservationListResponse.class);
    }

    @Override
    public ReservationListResponse getByEmail(String email) {
        DataRequest request = new DataRequest("reservation", "getByEmail", email);

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, ReservationListResponse.class);
    }

}
