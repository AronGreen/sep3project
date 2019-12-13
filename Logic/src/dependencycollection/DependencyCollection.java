package dependencycollection;

import serviceproviders.DummyPaymentServiceProvider;
import serviceproviders.IPaymentServiceProvider;
import serviceproviders.IPaymentUpdateCallback;

public class DependencyCollection {

    public static IPaymentServiceProvider getPaymentServiceProvider(IPaymentUpdateCallback callback) {
        return new DummyPaymentServiceProvider(callback);
    }

}
