package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import handlers.IReservationHandler;
import handlers.ITripHandler;
import handlers.ReservationHandler;
import handlers.TripHandler;
import models.Reservation;
import models.Trip;
import services.DataResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
//        DataResponse<Reservation[]> res = handler.getFiltered(null);
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


    @GET
    @Path("getForTrip/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response get(@PathParam("id") int id){
        // Send request and receive Response
        DataResponse<Reservation[]> res = handler.getByTripId(id);

        // Extract http response data
        int status = StatusMapper.map(res.getStatus());

        Gson gson = new GsonBuilder().setLenient().create();

        return Response
                .status(status)
                .entity(gson.toJson(res.getBody()))
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json){
        // Extract Trip from request
        Reservation t = Reservation.fromJson(json);

        // Send request and receive Response
        DataResponse<Reservation> res = handler.create(t);

        // Extract http response data
        int status = StatusMapper.map(res.getStatus());
        String entity = res.getBody() + "";

        Gson gson = new GsonBuilder().setLenient().create();

        return Response
                .status(status)
                .entity(gson.toJson(res.getBody()))
                .build();
    }
}
