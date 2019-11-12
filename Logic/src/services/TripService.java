package services;

import models.socket.SocketRequest;
import models.socket.SocketResponse;
import models.trip.CreateTripModel;
import models.trip.Trip;

public class TripService {
    private DataConnection dc = DataConnection.INSTANCE;
    private boolean dataLayerIsNotReady_Flag = false;

    public Trip getById(int id){
        // TODO: Business Logic here

//        SocketRequest request = new SocketRequest(1, "trip", "get", "{id: " + id + "}");
//        String  json = dc.sendRequest(request);
//        TripSocketResponse response = TripSocketResponse.fromJson(json);

        SocketRequest request = new SocketRequest("trip", "get", "{\"id\": " + id + "}");
        String  json = dc.sendRequest(request);
        SocketResponse<Trip> response = SocketResponse.fromJson(json, Trip.class);

        // TODO: Figure out the response types
//        if (response.getStatus().equals("Something bad")){
//            return null;
//        }

        return response.getBody();
    }

    public Trip getAll(){
        // TODO: Business Logic here

        // TODO: Figure out how to generify the responses
//        SocketRequest request = new SocketRequest(1, "trip", "getAll", "");
//        String  json = dc.sendRequest(request);
//        TripSocketResponse response = TripSocketResponse.fromJson(json);
//
//        // TODO: Figure out the response types
//        if (response.getStatus().equals("Something bad")){
//            return null;
//        }
//
//        return response.getBody();

        return null;
    }

    public boolean create(CreateTripModel model) {
        // TODO: Business Logic here

        SocketRequest request = new SocketRequest("trip", "create", model.toJson());
        if (dataLayerIsNotReady_Flag){
            String json = dc.sendRequest(request);
            SocketResponse<Trip> response = SocketResponse.fromJson(json, Trip.class);
            return response.getStatus().equals("200");
        }
        else {
            // TODO: Delete this when data is ready
            SocketResponse<Trip> response = new SocketResponse("200", Trip.class);
            return response.getStatus().equals("200");
        }
    }
}
