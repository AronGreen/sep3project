package controllers;

import handlers.AuthenticationHandler;
import handlers.IAuthenticationHandler;
import helpers.StringHelper;
import services.DataResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationController {

    private IAuthenticationHandler handler = new AuthenticationHandler();
    @Context HttpHeaders httpheaders;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(){

        try {
            String[] credentials = StringHelper.getCredentialsFromHttpHeaders(httpheaders);
            if (credentials == null ||
                    StringHelper.isNullOrEmpty(credentials[0]) ||
                    StringHelper.isNullOrEmpty(credentials[1])){
                return Response.status(Response
                        .Status.BAD_REQUEST)
                        .build();
            }

            DataResponse response = handler.authenticate(credentials[0], credentials[1]);

            return Response.status(StatusMapper.map(response.getStatus()))
                    .entity(response)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("Something went wrong here...").build();
        }
    }


}
