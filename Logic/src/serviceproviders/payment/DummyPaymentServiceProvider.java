package serviceproviders.payment;

import java.util.ArrayList;
import java.util.List;

public class DummyPaymentServiceProvider implements IPaymentServiceProvider {

    private IPaymentUpdateCallback callback;

    private List<Payment> payments;

    public DummyPaymentServiceProvider(IPaymentUpdateCallback cb) {
        if (cb == null) {
            throw new NullPointerException("Callback cannot be null");
        }
        callback = cb;

        payments = new ArrayList<>();
    }

    @Override
    public Payment issuePayment(Payment payment) {
        if (payment.getInvoiceId() == 0)
            throw new NullPointerException();
        payment.setId(getNextId());
        payment.setState(PaymentState.PENDING);
        payments.add(payment);

        callback.paymentStateUpdate(payment.getInvoiceId(), payment.getState());

        return payment;
    }

    @Override
    public Payment getPaymentByInvoiceId(int invoiceId) {
        for (Payment p : payments) {
            if (p.getInvoiceId() == invoiceId) {
                return p;
            }
        }

        return null;
    }

    @Override
    public Payment revokePayment(int invoiceId) {
        Payment p = getPaymentByInvoiceId(invoiceId);

        if (p == null) {
            return null;
        }

        switch (p.getState()) {
            case PAID:
                p.setState(PaymentState.REFUNDED);
                break;
            case PENDING:
            case REJECTED:
                p.setState(PaymentState.CANCELLED);
                break;
        }

        callback.paymentStateUpdate(invoiceId, p.getState());

        return p;
    }

    private int getNextId() {
        if (payments.isEmpty()) {
            return 0;
        }
        int id = payments.get(payments.size() - 1).getId();

        return ++id;
    }
}
