package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Trip {

    private int id;
    private String driverEmail;
    private String arrival;
    private String startAddress;
    private String destinationAddress;
    private double basePrice;
    private double perKmPrice;
    private double cancellationFee;
    private String rules;
    private int totalSeats;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
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
