package webapi.controllers;

import com.sun.deploy.net.BasicHttpRequest;
import com.sun.deploy.net.HttpRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/helloworld")
public class HelloWorldController {

    @GET
    @Produces("text/plain")
    public String getMessage(){
        return "Hello World";
    }


    @GET
    @Path("get/{x}")
    @Produces("text/plain")
    public String get(@PathParam("x") int number){
        if (number < 5){
            return "small number";
        }
        return "Big number";
    }

    @GET
    @Path("get")
    @Produces("text/plain")
    public String getget (@QueryParam("num")  int number){
        if (number < 5){
            return "small number";
        }
        return "Big number";
    }

    @POST
    @Path("message")
    @Produces("text/json")
    public Response message (@FormParam("name") String name, @FormParam("message") String message){
        String response   = name + " successfully sent message: " + message;
        return Response.status(200).entity(response).build();
    }
}
