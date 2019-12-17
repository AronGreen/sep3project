package controllers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import handlers.IAuthenticationHandler;
import handlers.ITripHandler;
import handlers.TripHandler;
import helpers.HttpResponseHelper;
import helpers.JsonConverter;
import jdk.nashorn.internal.objects.annotations.Getter;
import models.Trip;
import models.TripFilter;
import models.response.TripDetailsResponse;
import models.response.TripListResponse;
import models.response.TripResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/trips")
public class TripController {

    private ITripHandler handler = new TripHandler();
    private IAuthenticationHandler authenticationHandler = DependencyCollection.getAuthenticationHandler();

    @Context
    private HttpHeaders headers;

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json){
        // Extract Trip from request
        Trip t = Trip.fromJson(json);

        if (!authenticationHandler.getEmail(headers).equals(t.getDriverEmail())) {
            return HttpResponseHelper.getUnathourizedResponse();
        }

        // Send request and receive Response
        TripResponse res = handler.create(t);

        // Extract http response code
        int status = StatusMapper.map(res.getStatus());

        return Response
                .status(status)
                .entity(res.getBody())
                .build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        TripResponse res = handler.getById(id);
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return Response.status(ResponseStatus.HTTP_BAD_REQUEST).build();
        }

        if (!authenticationHandler.getEmail(headers).equals(res.getBody().getDriverEmail())) {
            return HttpResponseHelper.getUnathourizedResponse();
        }

        TripResponse response = handler.delete(id);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(JsonConverter.toJson(response.getBody()))
                .build();
    }

    @GET
    @Path("get/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFiltered(
            @DefaultValue("") @QueryParam("driverEmail") String driverEmail,
            @DefaultValue("") @QueryParam("passengerEmail") String passengerEmail,
            @DefaultValue("") @QueryParam("minimumArrivalDate") String minimumArrivalDate,
            @DefaultValue("") @QueryParam("maximumArrivalDate") String maximumArrivalDate,
            @DefaultValue("") @QueryParam("pickupAddress") String pickupAddress,
            @DefaultValue("") @QueryParam("dropoffAddress") String dropoffAddress,
            @DefaultValue("") @QueryParam("arrivalDate") String arrivalDate)
    {
        // Send request and receive ResponseS
        TripListResponse res = handler.getFiltered(new TripFilter(driverEmail, passengerEmail, minimumArrivalDate, maximumArrivalDate, pickupAddress, dropoffAddress, arrivalDate));

        // Extract http response data
        int status = StatusMapper.map(res.getStatus());
        String entity = JsonConverter.toJson(res.getBody());

        return Response
                .status(status)
                .entity(entity)
                .build();
    }

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response get(@PathParam("id") int id){
        // Send request and receive Response
        TripResponse res = handler.getById(id);

        // Extract http response code
        int status = StatusMapper.map(res.getStatus());

        return Response
                .status(status)
                .entity(JsonConverter.toJson(res.getBody()))
                .build();
    }

    @GET
    @Path("getTripDetails/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTripDetails(@PathParam("id") int id) {
        TripDetailsResponse res = handler.getTripDetails(id);

        return Response
                .status(StatusMapper.map(res.getStatus()))
                .entity(JsonConverter.toJson(res.getBody()))
                .build();
    }


}
