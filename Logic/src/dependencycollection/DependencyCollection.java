package dependencycollection;

import handlers.IInvoiceHandler;
import handlers.InvoiceHandler;
import serviceproviders.payment.DummyPaymentServiceProvider;
import serviceproviders.payment.IPaymentServiceProvider;
import serviceproviders.payment.IPaymentUpdateCallback;
import services.IInvoiceService;
import services.INotificationService;
import services.InvoiceService;
import services.NotificationService;

public class DependencyCollection {

    private static IInvoiceHandler invoiceHandler;
    private static IPaymentServiceProvider paymentServiceProvider;

    static {
        paymentServiceProvider = new DummyPaymentServiceProvider();
        invoiceHandler = new InvoiceHandler();
        ((DummyPaymentServiceProvider)paymentServiceProvider).setCallback(
                ((InvoiceHandler) invoiceHandler).callback());
    }

    public static IPaymentServiceProvider getPaymentServiceProvider() {
        return paymentServiceProvider;
    }

    public static INotificationService getNotificationService() {
        return new NotificationService();
    }

    public static IInvoiceService getInvoiceService() {
        return new InvoiceService();
    }

    public static IInvoiceHandler getInvoiceHandler() {
        return invoiceHandler;
    }
}
