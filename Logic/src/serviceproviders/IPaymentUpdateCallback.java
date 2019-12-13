package serviceproviders;

public interface IPaymentUpdateCallback {

    void paymentStateUpdate(int invoice, PaymentState newState);

}
