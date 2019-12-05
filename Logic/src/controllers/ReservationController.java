package controllers;

import handlers.IReservationHandler;
import handlers.ReservationHandler;
import helpers.JsonConverter;
import models.Reservation;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;
import models.response.StringResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/reservations")
public class ReservationController {

    private IReservationHandler handler = new ReservationHandler();

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

//    @GET
//    @Path("get")
//    @Produces(MediaType.APPLICATION_JSON )
//    public Response get(){
//        // Send request and receive Response
//        StringResponse<String> res = handler.getFiltered(null);
//
//        // Extract http response data
//        int status = StatusMapper.map(res.getStatus());
//        // String entity = res.getBody().toJson();
//        String entity = res.getBody() + "";// + " " + res.getBody().getDestinationAddress();
//
//        return Response
//                .status(status)
//                .entity(entity)
//                .build();
//    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json){
        // Extract Trip from request
        Reservation t = Reservation.fromJson(json);

        // Send request and receive Response
        ReservationResponse res = handler.create(t);

        // Extract http response code
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
