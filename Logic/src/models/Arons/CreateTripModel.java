package models.Arons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Non-entity model for creating a Trip.
 */
public class CreateTripModel {
    private double basePrice;
    private double pricePrKm;
    private double cancellationFee;
    private int estimatedTime;
    private int seats;

    public CreateTripModel(double basePrice, double pricePrKm, double cancellationFee, int estimatedTime, int seats) {
        this.basePrice = basePrice;
        this.pricePrKm = pricePrKm;
        this.cancellationFee = cancellationFee;
        this.estimatedTime = estimatedTime;
        this.seats = seats;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getPricePrKm() {
        return pricePrKm;
    }

    public void setPricePrKm(double pricePrKm) {
        this.pricePrKm = pricePrKm;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String toJson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.toJson(this);
    }

    public static CreateTripModel fromJson(String json) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson.fromJson(json, CreateTripModel.class);
    }
}
