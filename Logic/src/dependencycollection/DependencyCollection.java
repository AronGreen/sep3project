package dependencycollection;

import handlers.AuthenticationHandler;
import handlers.IAuthenticationHandler;
import handlers.IInvoiceHandler;
import handlers.InvoiceHandler;
import serviceproviders.navigation.BingMapsServiceProvider;
import serviceproviders.navigation.INavigationServiceProvider;
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
    private static INavigationServiceProvider navigationServiceProvider = new BingMapsServiceProvider();
    private static IAuthenticationHandler authenticationHandler = new AuthenticationHandler();

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

    public static INavigationServiceProvider getNavigationServiceProvider() {
        return navigationServiceProvider;
    }

    public static IAuthenticationHandler getAuthenticationHandler() {
        return authenticationHandler;
    }
}
