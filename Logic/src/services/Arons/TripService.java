package services.Arons;

import models.Arons.CreateTripModel;
import models.Arons.Trip;
import services.DataConnection;
import services.DataRequest;
import services.DataResponse;

public class TripService {
    private DataConnection dc = DataConnection.INSTANCE;
    private boolean dataLayerIsNotReady_Flag = false;

    public Trip getById(int id){
        // TODO: Business Logic here

        DataRequest request = new DataRequest("trip", "get", "" + id);
        String  json = dc.sendRequest(request);
        DataResponse<Trip> response = DataResponse.fromJson(json, Trip.class);

        return response.getBody();
    }

    public Trip getAll(){
        // TODO: Business Logic here

        // TODO: Figure out how to generify the responses
//        DataRequest request = new DataRequest(1, "trip", "getAll", "");
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

        DataRequest request = new DataRequest("trip", "create", model.toJson());
        if (dataLayerIsNotReady_Flag){
            String json = dc.sendRequest(request);
            DataResponse<Trip> response = DataResponse.fromJson(json, Trip.class);
            return response.getStatus().equals("200");
        }
        else {
            // TODO: Delete this when data is ready
            DataResponse<Trip> response = new DataResponse("200", Trip.class);
            return response.getStatus().equals("200");
        }
    }
}
