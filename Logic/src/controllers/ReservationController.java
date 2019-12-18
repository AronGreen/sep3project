package controllers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import handlers.*;
import helpers.HttpResponseHelper;
import helpers.JsonConverter;
import models.Reservation;
import models.Trip;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;
import models.response.StringResponse;
import models.response.TripResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/reservations")
public class ReservationController {

    private IReservationHandler handler = new ReservationHandler();
    private IAuthenticationHandler authenticationHandler = DependencyCollection.getAuthenticationHandler();
    private ITripHandler tripHandler = new TripHandler();

    @Context
    private HttpHeaders headers;

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json){
        Reservation reservation = Reservation.fromJson(json);

        if (!authenticationHandler.getEmail(headers)
                .equals(reservation.getPassengerEmail()))
            return HttpResponseHelper.getUnathourizedResponse();

        ReservationResponse res = handler.create(reservation);

        int status = StatusMapper.map(res.getStatus());

        return Response
                .status(status)
                .entity(res.getBody())
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String json) {
        Reservation reservation = JsonConverter.fromJson(json, Reservation.class);

        TripResponse res = tripHandler.getById(reservation.getTripId());
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS))
            return Response.status(ResponseStatus.HTTP_BAD_REQUEST).build();

        if (!authenticationHandler.getEmail(headers).equals(res.getBody().getDriverEmail()))
            return HttpResponseHelper.getUnathourizedResponse();

        ReservationResponse response = handler.update(reservation);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        ReservationResponse res = handler.getById(id);
        if (res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return Response.status(ResponseStatus.HTTP_BAD_REQUEST).build();
        }
        if (!authenticationHandler.getEmail(headers).equals(res.getBody().getPassengerEmail()))
            return HttpResponseHelper.getUnathourizedResponse();

        ReservationResponse response = handler.delete(id);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        ReservationResponse response = handler.getById(id);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("getForTrip/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response get(@PathParam("id") int id){
        // Send request and receive Response
        ReservationListResponse res = handler.getByTripId(id);

        // Extract http response code
        int status = StatusMapper.map(res.getStatus());

        return Response
                .status(status)
                .entity(res.getBody())
                .build();
    }

    @GET
    @Path("getByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEmail(@PathParam("email") String email) {
        ReservationListResponse response = handler.getByEmail(email);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

}
