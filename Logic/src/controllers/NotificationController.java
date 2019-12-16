package controllers;

import dependencycollection.DependencyCollection;
import handlers.IAuthenticationHandler;
import helpers.HttpResponseHelper;
import helpers.JsonConverter;
import models.response.NotificationListResponse;
import services.INotificationService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notifications")
public class NotificationController {

    // TODO replace this with a newly created handler.. I'm too burnt out for this.
    private INotificationService notificationService = DependencyCollection.getNotificationService();
    private IAuthenticationHandler authenticationHandler = DependencyCollection.getAuthenticationHandler();

    @Context
    private HttpHeaders headers;

    @GET
    @Path("/get/{accountEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("accountEmail") String accountEmail, @DefaultValue("-1") @QueryParam("limit") int limit) {
        if (!authenticationHandler.getEmail(headers).equals(accountEmail))
            return HttpResponseHelper.getUnathourizedResponse();

        NotificationListResponse res = limit == -1 ?
                notificationService.getAllByAccountEmail(accountEmail) :
                notificationService.getAllByAccountEmail(accountEmail, limit);

        return Response
                .status(StatusMapper.map(res.getStatus()))
                .entity(JsonConverter.toJson(res.getBody()))
                .build();
    }

}
