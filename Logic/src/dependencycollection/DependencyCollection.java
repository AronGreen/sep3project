package dependencycollection;

import handlers.IInvoiceHandler;
import handlers.InvoiceHandler;
import models.Invoice;
import serviceproviders.DummyPaymentServiceProvider;
import serviceproviders.IPaymentServiceProvider;
import serviceproviders.IPaymentUpdateCallback;
import services.IInvoiceService;
import services.INotificationService;
import services.InvoiceService;
import services.NotificationService;

public class DependencyCollection {

    public static IPaymentServiceProvider getPaymentServiceProvider(IPaymentUpdateCallback callback) {
        return new DummyPaymentServiceProvider(callback);
    }

    public static INotificationService getNotificationService() {
        return new NotificationService();
    }

    public static IInvoiceService getInvoiceService() {
        return new InvoiceService();
    }

    public static IInvoiceHandler getInvoiceHandler() {
        return new InvoiceHandler();
    }
}
