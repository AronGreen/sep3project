package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.trip.CreateTripModel;
import models.trip.Trip;
import services.TripService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/trips")
public class TripController {
    private TripService tripService = new TripService();

    // https://download.oracle.com/otn-pub/jcp/jaxrs-2_0-fr-eval-spec/jsr339-jaxrs-2.0-final-spec.pdf
    @Context
    private UriInfo info;

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private ServletContext servletContext;

    @Context
    private SecurityContext securityContext;

    @Context
    private Request request;

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response get(@PathParam("id") int id){
        Trip trip = tripService.getById(id);
        // TODO: Handle errors
        return Response
                .status(200)
                .entity(trip.toJson())
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json){
        CreateTripModel model = CreateTripModel.fromJson(json);

        boolean success = tripService.create(model);

        if (success){
            return Response.status(200).build();
        }
        else {
            return Response.status(400).build();
        }
    }
}
