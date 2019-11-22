package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import handlers.ITripHandler;
import handlers.TripHandler;
import helpers.JsonConverter;
import jdk.net.SocketFlow;
import models.Trip;
import models.TripFilter;
import services.DataResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
        DataResponse<String> res = handler.create(t);

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
        DataResponse<String> response = handler.delete(id);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON )
    public Response getFiltered(){
        // Send request and receive Response
        DataResponse<String> res = handler.getFiltered(new TripFilter());

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
        DataResponse<String> res = handler.getById(id);

        // Extract http response code
        int status = StatusMapper.map(res.getStatus());

        return Response
                .status(status)
                .entity(res.getBody())
                .build();
    }


}
