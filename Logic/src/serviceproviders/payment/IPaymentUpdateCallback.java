package serviceproviders.payment;

public interface IPaymentUpdateCallback {

    void paymentStateUpdate(int invoice, PaymentState newState);

}
