package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpers.DateTimeHelper;
import models.Reservation;
import models.Trip;
import models.TripDetails;
import serviceproviders.navigation.BingMapsServiceProvider;

import javax.print.attribute.standard.Destination;
import javax.ws.rs.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    public String getget (@QueryParam("num") int number){
        if (number < 5){
            return "small number";
        }
        return "Big number";
    }


    @GET
    @Path("bing")
    @Produces("application/json")
    public String bing (){
        BingMapsServiceProvider bingMapsServiceProvider = new BingMapsServiceProvider();

        Trip trip = new Trip();
        trip.setStartAddress("Fussingsvej 8, 8700 Horsens");
        trip.setDestinationAddress("Chr M Østergaards Vej 4, 8700 Horsens");
        trip.setArrival(DateTimeHelper.toString(LocalDateTime.now()));

        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation res1 = new Reservation();
        res1.setPickupAddress("Sundvej 109, 8700 Horsens");
        res1.setDropoffAddress("Hede Nielsens Vej 2, 8700 Horsens");
        reservations.add(res1);

        TripDetails td = bingMapsServiceProvider.getTripDetails(trip, reservations);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(td);
    }

}
