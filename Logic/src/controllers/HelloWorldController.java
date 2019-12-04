package controllers;

import javax.ws.rs.*;

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

//    @POST
//    @Path("message")
//    @Produces("text/json")
//    public Response message (@FormParam("name") String name, @FormParam("message") String message){
////
////        String response   = name + " successfully sent message: " + message;
//        SampleFormModel model = new SampleFormModel(name, message);
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        return Response.status(200).entity(gson.toJson(model)).build();
//    }
}
