package controllers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import handlers.IAuthenticationHandler;
import handlers.IReviewHandler;
import handlers.ReviewHandler;
import helpers.HttpResponseHelper;
import helpers.JsonConverter;
import models.Review;
import models.response.ReviewListResponse;
import models.response.ReviewResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/reviews")
public class ReviewController {

    private IReviewHandler handler = new ReviewHandler();
    private IAuthenticationHandler authenticationHandler = DependencyCollection.getAuthenticationHandler();

    @Context
    private HttpHeaders headers;

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json) {
        Review review = JsonConverter.fromJson(json, Review.class);

        if (!authenticationHandler.getEmail(headers).equals(review.getReviewerEmail())) {
            return HttpResponseHelper.getUnathourizedResponse();
        }

        ReviewResponse response = handler.create(review);

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
        ReviewResponse res = handler.getById(id);
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return Response.status(ResponseStatus.HTTP_BAD_REQUEST).build();
        }

        if (!authenticationHandler.getEmail(headers).equals(res.getBody().getReviewerEmail()))
            return HttpResponseHelper.getUnathourizedResponse();

        ReviewResponse response = handler.delete(id);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("getAllByRevieweeEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByRevieweeEmail(@PathParam("email") String email) {
        ReviewListResponse response = handler.getAllByRevieweeEmail(email);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("getAllByReviewerEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByReviewerEmail(@PathParam("email") String email) {
        ReviewListResponse response = handler.getAllByReviewerEmail(email);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }
    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response getById(@PathParam("id") int id){
        ReviewResponse res = handler.getById(id);

        int status = StatusMapper.map(res.getStatus());

        return Response
                .status(status)
                .entity(res.getBody())
                .build();
    }





}
