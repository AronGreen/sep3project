package models;

public class TripFilter {

    private String driverEmail;
    private String passengerEmail;
    private String minimumArrivalDate;
    private String maximumArrivalDate;
    private String pickupAddress;
    private String dropoffAddress;
    private String arrivalDate;

    public TripFilter(String driverEmail, String passengerEmail, String minimumArrivalDate, String maximumArrivalDate, String pickupAddress, String dropoffAddress, String arrivalDate) {
        this.driverEmail = driverEmail;
        this.passengerEmail = passengerEmail;
        this.minimumArrivalDate = minimumArrivalDate;
        this.maximumArrivalDate = maximumArrivalDate;
        this.pickupAddress = pickupAddress;
        this.dropoffAddress = dropoffAddress;
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDropoffAddress() {
        return dropoffAddress;
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
