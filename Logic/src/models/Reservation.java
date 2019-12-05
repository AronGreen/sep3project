package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reservation {

    private int id;
    private Trip trip;
    private int tripId;
    private String passengerEmail;
    private Account passenger;
    private String pickupAddress;
    private String dropoffAddress;
    private String state;
    private String pickupTime;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Account getPassenger() {
        return passenger;
    }

    public void setPassenger(Account passenger) {
        this.passenger = passenger;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDropoffAddress() {
        return dropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String toJson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(this);
    }

    public static Reservation fromJson(String json) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.fromJson(json, Reservation.class);
    }
}
