package services;

import models.socket.SocketRequest;
import models.Trip;
import models.socket.TripSocketResponse;

public class TripService {
    private DataConnection dc = DataConnection.INSTANCE;

    public Trip getById(int id){
        SocketRequest request = new SocketRequest(1, "trip", "get", "{id: " + id + "}");
        String  json = dc.sendRequest(request);
        TripSocketResponse response = TripSocketResponse.fromJson(json);

        // TODO: Figure out the response types
        if (response.getStatus().equals("Something bad")){
            return null;
        }

        return response.getBody();
    }
}
