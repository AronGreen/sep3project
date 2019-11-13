package services;

import models.Trip;
import models.TripFilter;

import javax.xml.crypto.Data;

public class TripService implements ITripService {

    private DataConnection connection;

    public TripService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public DataResponse<Trip> create(Trip trip) {
        // Construct the Request based on the Trip and the method
        DataRequest req = new DataRequest("trip", "create", trip.toJson());

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        DataResponse<Trip> res = DataResponse.fromJson(json, Trip.class);

        return res;
    }

    @Override
    public DataResponse<Trip> delete(int id) {
        // Construct the Request based on the id and the method
        DataRequest req = new DataRequest("trip", "delete", "" + id);

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        DataResponse<Trip> res = DataResponse.fromJson(json, Trip.class);

        return res;
    }

    @Override
    public DataResponse<Trip> getById(int id) {
        // Construct the Request based on the id and the method
        DataRequest req = new DataRequest("trip", "getById", id + "");

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        DataResponse<Trip> res = DataResponse.fromJson(json, Trip.class);

        return res;
    }

    @Override
    public DataResponse<Trip[]> getFiltered(TripFilter filter) {
        // Construct the Request based on the filter and the method
        // TODO add filter json
        DataRequest req = new DataRequest("trip", "getFiltered", null);

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        DataResponse<Trip[]> res = DataResponse.fromJson(json, Trip[].class);

        return res;
    }
}
