package handlers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import models.Account;
import models.Invoice;
import models.Notification;
import models.NotificationType;
import models.response.InvoiceResponse;
import serviceproviders.*;
import services.*;

public class InvoiceHandler implements IInvoiceHandler {

    private IInvoiceService invoiceService = new InvoiceService();
    private IPaymentServiceProvider paymentServiceProvider;
    private IAccountService accountService = new AccountService();
    private INotificationService notificationService = DependencyCollection.getNotificationService();

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

    @Override
    public boolean pay(int invoiceId) {
        return invoiceService.updateState(invoiceId, PaymentState.PAID.toString())
                .getStatus().equals(ResponseStatus.SOCKET_SUCCESS);
    }

    @Override
    public boolean revoke(int invoiceId) {
        return paymentServiceProvider.revokePayment(invoiceId) != null;
    }

    private IPaymentUpdateCallback callback() {
        return (invoiceId, state) -> {
            // Handle if invoice does not exist
            InvoiceResponse res = invoiceService.getById(invoiceId);
            if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
                return;
            }
            Invoice invoice = res.getBody();

            // Create notifications according to the state of the payment
            NotificationType type = null;
            switch (state) {
                case PENDING: type = NotificationType.INVOICE_PENDING; break;
                case CANCELLED: type = NotificationType.INVOICE_CANCELLED; break;
                case REFUNDED: type = NotificationType.INVOICE_REFUNDED; break;
                case REJECTED: type = NotificationType.INVOICE_REJECTED; break;
                case PAID:
                    notificationService.deleteAllByTypeAndItemId("invoice", invoiceId);
                    break;
            }

            if (type != null) {
                notificationService.create(new Notification(
                        invoice.getPayerEmail(),
                        type,
                        invoiceId
                ));
            }
            invoiceService.updateState(invoiceId, state.toString());
        };
    }
}
