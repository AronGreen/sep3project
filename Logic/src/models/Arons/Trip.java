package models.Arons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class Trip extends Entity {

    private int id;
    private User user;
    private Date arrival;
    private String startAddress;
    private String destinationAddress;
    private double basePrice;
    private double perKmPrice;
    private double cancellationFee;
    private String[] rules;
    private int totalSeats;
    private String description;

    public Trip(int id, String createDate, String updateDate, String deleted, int id1, User user,
                Date arrival, String startAddress, String destinationAddress, double basePrice,
                double perKmPrice, double cancellationFee, String[] rules, int totalSeats,
                String description) {
        super(id, createDate, updateDate, deleted);
        this.id = id1;
        this.user = user;
        this.arrival = arrival;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.basePrice = basePrice;
        this.perKmPrice = perKmPrice;
        this.cancellationFee = cancellationFee;

        // TODO there is an encapsulation error because the outside object accesses the same array
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

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setRules(String[] rules) {
        this.rules = rules;
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
