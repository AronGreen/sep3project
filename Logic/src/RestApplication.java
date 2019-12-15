import controllers.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/")
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(HelloWorldController.class);
        h.add(TripController.class);
        h.add(AuthenticationController.class);
        h.add(AccountController.class);
        h.add(ReservationController.class);
        h.add(InvoiceController.class);
        h.add(NotificationController.class);
        return h;
    }
}
