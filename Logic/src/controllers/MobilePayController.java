package controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Path("/mobilePay")
public class MobilePayController {

    @GET
    @Path("consent")
    public Response getMessage(){
        return Response
                .status(301)
                .build();

    }
}
