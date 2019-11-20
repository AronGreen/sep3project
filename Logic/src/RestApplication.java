import controllers.AuthController;
import controllers.HelloWorldController;
import controllers.ReservationController;
import controllers.TripController;
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
        h.add(AuthController.class);
        h.add(ReservationController.class);
        return h;
    }
}
