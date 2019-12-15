package serviceproviders.payment;

public interface IPaymentServiceProvider {

    Payment issuePayment(Payment payment);
    Payment getPaymentByInvoiceId(int invoiceId);
    Payment revokePayment(int invoiceId);

}
