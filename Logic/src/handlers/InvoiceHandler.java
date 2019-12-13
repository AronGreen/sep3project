package handlers;

import dependencycollection.DependencyCollection;
import models.Account;
import models.Invoice;
import serviceproviders.DummyPaymentServiceProvider;
import serviceproviders.IPaymentServiceProvider;
import serviceproviders.IPaymentUpdateCallback;
import serviceproviders.Payment;
import services.AccountService;
import services.IAccountService;
import services.IInvoiceService;
import services.InvoiceService;

public class InvoiceHandler implements IInvoiceHandler {

    private IInvoiceService invoiceService = new InvoiceService();
    private IPaymentServiceProvider paymentServiceProvider;
    private IAccountService accountService = new AccountService();

    public InvoiceHandler() {
        paymentServiceProvider = DependencyCollection.getPaymentServiceProvider(callback());
    }

    @Override
    public Invoice create(Invoice invoice) {
        invoice = invoiceService.create(invoice).getBody();

        Payment payment = new Payment(
                invoice.getId(),
                accountService.getByEmail(invoice.getPayerEmail()).getBody().getPaymentMethod(),
                accountService.getByEmail(invoice.getPayeeEmail()).getBody().getPaymentMethod(),
                invoice.getAmount());
        paymentServiceProvider.issuePayment(payment);

        return invoice;
    }

    @Override
    public boolean accountHasUnpaidInvoice(String accountEmail) {
        return !invoiceService.getAllUnpaidByPayerEmail(accountEmail).getBody().isEmpty();
    }

    private IPaymentUpdateCallback callback() {
        return (id, state) -> {
            // TODO remove the notification of having to pay when a user pays an invoice
            invoiceService.updateState(id, state.toString());
        };
    }
}
