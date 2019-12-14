package dependencycollection;

import serviceproviders.DummyPaymentServiceProvider;
import serviceproviders.IPaymentServiceProvider;
import serviceproviders.IPaymentUpdateCallback;
import services.INotificationService;
import services.NotificationService;

public class DependencyCollection {

    public static IPaymentServiceProvider getPaymentServiceProvider(IPaymentUpdateCallback callback) {
        return new DummyPaymentServiceProvider(callback);
    }

    public static INotificationService getNotificationService() {
        return new NotificationService();
    }

}
