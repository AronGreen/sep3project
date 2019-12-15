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
import java.util.List;

@Path("/helloworld")
public class HelloWorldController {

    @GET
    @Produces("text/plain")
    public String getMessage() {
        return "Hello World";
    }

    @GET
    @Path("get/{x}")
    @Produces("text/plain")
    public String get(@PathParam("x") int number) {
        if (number < 5) {
            return "small number";
        }
        return "Big number";
    }

    @GET
    @Path("get")
    @Produces("text/plain")
    public String getget(@QueryParam("num") int number) {
        if (number < 5) {
            return "small number";
        }
        return "Big number";
    }


    @GET
    @Path("bing")
    @Produces("application/json")
    public String bing() {
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

    @GET
    @Path("bing2")
    @Produces("application/json")
    public String bing2() {
        BingMapsServiceProvider bingMapsServiceProvider = new BingMapsServiceProvider();


        Trip trip1 = new Trip();
        trip1.setStartAddress("Fussingsvej 8, 8700 Horsens");
        trip1.setDestinationAddress("Skovbrynet 5, 8000 Aarhus");
        trip1.setArrival(DateTimeHelper.toString(LocalDateTime.now()));

        Trip trip2 = new Trip();
        trip2.setStartAddress("Skovbrynet 5, 8000 Aarhus");
        trip2.setDestinationAddress("Fussingsvej 8, 8700 Horsens");
        trip2.setArrival(DateTimeHelper.toString(LocalDateTime.now()));

        List<Trip> trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);

        Reservation res1 = new Reservation();
        res1.setPickupAddress("Egebjergvej 21, 8751 Gedved");
        res1.setDropoffAddress("Skovlundgårdsvej 51, 8260 Viby J");

        List<Trip> filteredTrips = bingMapsServiceProvider.getTripsForReservation(trips, res1);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(filteredTrips);
    }

    @GET
    @Path("bing3")
    @Produces("application/json")
    public String bing3() {
        BingMapsServiceProvider bingMapsServiceProvider = new BingMapsServiceProvider();


        Trip trip1 = new Trip();
        trip1.setStartAddress("Fussingsvej 8, 8700 Horsens");
        trip1.setDestinationAddress("Skovbrynet 5, 8000 Aarhus");
        trip1.setArrival(DateTimeHelper.toString(LocalDateTime.now()));

        Trip trip2 = new Trip();
        trip2.setStartAddress("Skovbrynet 5, 8000 Aarhus");
        trip2.setDestinationAddress("Fussingsvej 8, 8700 Horsens");
        trip2.setArrival(DateTimeHelper.toString(LocalDateTime.now()));

        List<Trip> trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);

        Reservation res1 = new Reservation();
        res1.setPickupAddress("Skovlundgårdsvej 51, 8260 Viby J");
        res1.setDropoffAddress("Egebjergvej 21, 8751 Gedved");

        List<Trip> filteredTrips = bingMapsServiceProvider.getTripsForReservation(trips, res1);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(filteredTrips);
    }
}
