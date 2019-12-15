package serviceproviders;

public interface IPaymentServiceProvider {

    Payment issuePayment(Payment payment);
    Payment getPaymentByInvoiceId(int invoiceId);
    Payment revokePayment(int invoiceId);

}
