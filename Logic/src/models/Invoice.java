package models;

import serviceproviders.PaymentState;

public class Invoice {

    private int id;
    private int tripId;
    private int reservationId;
    private String payerEmail;
    private String payeeEmail;
    private String type;
    private double amount;
    private String state;

    public Invoice(int tripId, int reservationId, String payerEmail, String payeeEmail, String type, double amount) {
        this.tripId = tripId;
        this.reservationId = reservationId;
        this.payerEmail = payerEmail;
        this.payeeEmail = payeeEmail;
        this.type = type;
        this.amount = amount;
        this.state = "";
    }

    public Invoice(int id, String state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getTripId() {
        return tripId;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getState() {
        return state;
    }
}
