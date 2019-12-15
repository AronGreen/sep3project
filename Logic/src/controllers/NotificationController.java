package controllers;

import dependencycollection.DependencyCollection;
import helpers.JsonConverter;
import models.response.NotificationListResponse;
import services.INotificationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notifications")
public class NotificationController {

    // TODO replace this with a newly created handler.. I'm too burnt out for this.
    private INotificationService notificationService = DependencyCollection.getNotificationService();

    @GET
    @Path("/get/{accountEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("accountEmail") String accountEmail, @DefaultValue("-1") @QueryParam("limit") int limit) {
        NotificationListResponse res = limit == -1 ?
                notificationService.getAllByAccountEmail(accountEmail) :
                notificationService.getAllByAccountEmail(accountEmail, limit);

        return Response
                .status(StatusMapper.map(res.getStatus()))
                .entity(JsonConverter.toJson(res.getBody()))
                .build();
    }

}
