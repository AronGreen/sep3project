package services;

import helpers.JsonConverter;
import models.Trip;
import models.TripFilter;
import models.response.TripListResponse;
import models.response.TripResponse;
import org.omg.CORBA.TRANSACTION_MODE;

import javax.xml.crypto.Data;

public class TripService implements ITripService {

    private DataConnection connection;

    public TripService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public TripResponse create(Trip trip) {
        // Construct the Request based on the Trip and the method
        DataRequest req = new DataRequest("trip", "create", trip.toJson());

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        return JsonConverter.fromJson(json, TripResponse.class);
    }

    @Override
    public TripResponse delete(int id) {
        // Construct the Request based on the id and the method
        DataRequest req = new DataRequest("trip", "delete", "" + id);

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        return JsonConverter.fromJson(json, TripResponse.class);
    }

    @Override
    public TripResponse getById(int id) {
        // Construct the Request based on the id and the method
        DataRequest req = new DataRequest("trip", "getById", id + "");

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        // Construct the Response
        return JsonConverter.fromJson(json, TripResponse.class);
    }

    @Override
    public TripListResponse getFiltered(TripFilter filter) {
        // Construct the Request based on the filter and the method
        // TODO add filter json
        DataRequest req = new DataRequest("trip", "getFiltered", JsonConverter.toJson(filter));

        // Send the Request and receive the response json
        String json = connection.sendRequest(req);

        return JsonConverter.fromJson(json, TripListResponse.class);
    }
}
