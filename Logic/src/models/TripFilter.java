package models;

public class TripFilter {

    private String driverEmail;
    private String passengerEmail;
    private String minimumArrivalDate;
    private String maximumArrivalDate;

    public TripFilter(String driverEmail, String passengerEmail, String minimumArrivalDate, String maximumArrivalDate) {
        this.driverEmail = driverEmail;
        this.passengerEmail = passengerEmail;
        this.minimumArrivalDate = minimumArrivalDate;
        this.maximumArrivalDate = maximumArrivalDate;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public String getMinimumArrivalDate() {
        return minimumArrivalDate;
    }

    public String getMaximumArrivalDate() {
        return maximumArrivalDate;
    }
}
