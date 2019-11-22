package services;

import helpers.JsonConverter;
import models.Trip;
import models.TripFilter;

import javax.xml.crypto.Data;

public class TripService implements ITripService {

    private DataConnection connection;

    public TripService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public DataResponse<String> create(Trip trip) {
        // Construct the Request based on the Trip and the method
        DataRequest req = new DataRequest("trip", "create", trip.toJson());

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        DataResponse<String> res = DataResponse.fromJson(json, String.class);

        return res;
    }

    @Override
    public DataResponse<String> delete(int id) {
        // Construct the Request based on the id and the method
        DataRequest req = new DataRequest("trip", "delete", "" + id);

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        DataResponse<String> res = DataResponse.fromJson(json, String.class);

        return res;
    }

    @Override
    public DataResponse<String> getById(int id) {
        // Construct the Request based on the id and the method
        DataRequest req = new DataRequest("trip", "getById", id + "");

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        DataResponse<String> res = DataResponse.fromJson(json, String.class);

        return res;
    }

    @Override
    public DataResponse<String> getFiltered(TripFilter filter) {
        // Construct the Request based on the filter and the method
        // TODO add filter json
        DataRequest req = new DataRequest("trip", "getFiltered", JsonConverter.toJson(filter));

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        return DataResponse.fromJson(json, String.class);
    }
}
