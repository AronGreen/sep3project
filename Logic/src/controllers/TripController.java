package controllers;

import handlers.ITripHandler;
import handlers.TripHandler;
import helpers.JsonConverter;
import models.Trip;
import models.TripFilter;
import models.response.TripListResponse;
import models.response.TripResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/trips")
public class TripController {

    private ITripHandler handler = new TripHandler();

    // https://download.oracle.com/otn-pub/jcp/jaxrs-2_0-fr-eval-spec/jsr339-jaxrs-2.0-final-spec.pdf
//    @Context
//    private UriInfo info;
//
//    @Context
//    private HttpServletRequest servletRequest;
//
//    @Context
//    private ServletContext servletContext;
//
//    @Context
//    private SecurityContext securityContext;
//
//    @Context
//    private Request request;
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json){
        // Extract Trip from request
        Trip t = Trip.fromJson(json);

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
        TripResponse response = handler.delete(id);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
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
            @DefaultValue("") @QueryParam("dropoffAddress") String dropoffAddress)
    {
        // Send request and receive ResponseS
        TripListResponse res = handler.getFiltered(new TripFilter(driverEmail, passengerEmail, minimumArrivalDate, maximumArrivalDate, pickupAddress, dropoffAddress));

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
                .entity(res.getBody())
                .build();
    }


}
