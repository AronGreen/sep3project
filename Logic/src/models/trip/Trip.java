package models.trip;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Entity;
import models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class Trip extends Entity {

    private int id;

    private User user;

    private Date arrival;

    private double startX;

    private double startY;

    private double destinationX;

    private double destinationY;

    private double basePrice;

    private double perKmPrice;

    private double cancellationFee;

    private ArrayList<String> rules;

    private int totalSeats;

    private String description;

    public Trip(int id, String createDate, String updateDate, String deleted, int id1, User user, Date arrival, double startX, double startY, double destinationX, double destinationY, double basePrice, double perKmPrice, double cancellationFee, ArrayList<String> rules, int totalSeats, String description) {
        super(id, createDate, updateDate, deleted);
        this.id = id1;
        this.user = user;
        this.arrival = arrival;
        this.startX = startX;
        this.startY = startY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.basePrice = basePrice;
        this.perKmPrice = perKmPrice;
        this.cancellationFee = cancellationFee;
        this.rules = rules;
        this.totalSeats = totalSeats;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getPerKmPrice() {
        return perKmPrice;
    }

    public void setPerKmPrice(double perKmPrice) {
        this.perKmPrice = perKmPrice;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(this);
    }

    public static Trip fromJson(String json) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.fromJson(json, Trip.class);
    }
}
