package controllers;

import com.google.gson.JsonNull;
import com.sun.media.jfxmediaimpl.MediaUtils;
import handlers.AccountHandler;
import handlers.IAccountHandler;
import helpers.JsonConverter;
import models.Account;
import services.DataResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/accounts")
public class AccountController {

    private IAccountHandler handler = new AccountHandler();

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json) {
        Account account = JsonConverter.fromJson(json, Account.class);

        DataResponse<String> response = handler.create(account);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String json) {
        Account account = JsonConverter.fromJson(json, Account.class);

        DataResponse<String> response = handler.update(account);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @DELETE
    @Path("delete/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("email") String email) {
        DataResponse<String> response = handler.delete(email);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        DataResponse<String> response = handler.getAll();

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("get/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEmail(@PathParam("email") String email) {
        DataResponse<String> response = handler.getByEmail(email);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

    @GET
    @Path("getPassword/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPasswordByEmail(@PathParam("email") String email) {
        DataResponse<String> response = handler.getPasswordByEmail(email);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

}
