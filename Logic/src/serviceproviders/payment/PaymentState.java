package serviceproviders.payment;

public enum PaymentState {

    PENDING("Pending"),
    PAID("Paid"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled"),
    REFUNDED("Refunded"),
    ERROR("Error");

    private String name;

    PaymentState(String str) {
        name = str;
    }

    @Override
    public String toString() {
        return name;
    }
}
