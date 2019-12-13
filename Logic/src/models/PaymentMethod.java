package models;

public class PaymentMethod {

    private String phoneNumber;

    public PaymentMethod(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
