package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reservation {

    private int id;
    private Trip trip;
    private User passenger;
    private String pickupAddress;
    private String dropoffAddress;
    private String approved;
    private String pickupTime;

    public Reservation(int id, Trip trip, User passenger, String pickupAddress, String dropoffAddress, String approved, String pickupTime) {
        this.id = id;
        this.trip = trip;
        this.passenger = passenger;
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.approved = approved;
        this.pickupTime = pickupTime;
    }

    public Reservation(Trip trip, User passenger, String pickupAddress, String dropoffAddress) {
        this.trip = trip;
        this.passenger = passenger;
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
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

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
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

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
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
