package services;

import models.SocketRequest;
import models.SocketResponse;
import models.Trip;
import models.TripSocketResponse;

public class TripService {
    private DataConnection dc = DataConnection.INSTANCE;

    public Trip getById(int id){
        SocketRequest request = new SocketRequest(1, "trip", "get", "{id: " + id + "}");
        TripSocketResponse response = dc.sendRequest(request);
        return response.getBody();
    }
}
