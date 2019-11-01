package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.SampleFormModel;
import models.Trip;
import services.TripService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trips")
public class TripController {
    private TripService tripService = new TripService();

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response get(@PathParam("id") int id){
        Trip trip = tripService.getById(id);
        return Response
                .status(200)
                .entity(trip.toJson())
                .build();
    }
}
