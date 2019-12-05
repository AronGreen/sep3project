package controllers;

import handlers.AccountHandler;
import handlers.IAccountHandler;
import helpers.JsonConverter;
import models.Account;
import models.response.AccountListResponse;
import models.response.AccountResponse;
import models.response.StringResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountController {

    private IAccountHandler handler = new AccountHandler();

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String json) {
        Account account = JsonConverter.fromJson(json, Account.class);

        AccountResponse response = handler.create(account);

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

        AccountResponse response = handler.update(account);

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
        AccountResponse response = handler.delete(email);

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
        AccountListResponse response = handler.getAll();

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
        AccountResponse response = handler.getByEmail(email);

        int status = StatusMapper.map(response.getStatus());

        String entity = JsonConverter.toJson(response.getBody());
        return Response
                .status(status)
                .entity(entity)
                .build();
    }

    @GET
    @Path("getPassword/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPasswordByEmail(@PathParam("email") String email) {
        StringResponse response = handler.getPasswordByEmail(email);

        int status = StatusMapper.map(response.getStatus());

        return Response
                .status(status)
                .entity(response.getBody())
                .build();
    }

}
