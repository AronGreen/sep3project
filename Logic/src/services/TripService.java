package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Trip;
import models.SocketRequest;
import models.SocketResponse;

public class TripService {
    private DataConnection dc = DataConnection.INSTANCE;

    public Trip getById(int id){
        SocketRequest request = new SocketRequest(1, "trip", "get", "{id: " + id + "}");
        SocketResponse response = dc.sendRequest(request);
        System.out.println(response.getBody());
        return Trip.fromJson(response.getBody());
    }
}
