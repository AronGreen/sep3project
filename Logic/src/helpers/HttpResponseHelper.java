package helpers;

import constants.ResponseStatus;

import javax.ws.rs.core.Response;

public class HttpResponseHelper {

    public static Response getUnathourizedResponse() {
        return Response.status(ResponseStatus.HTTP_UNAUTHORIZED).build();
    }

}
