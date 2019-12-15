package serviceproviders.navigation;

import java.util.ArrayList;
import java.util.List;

class BingMapResource {
    private double travelDistance;
    private int travelDuration;
    private ArrayList<String> waypointsOrder;
    private ArrayList<RouteLeg> routeLegs;
    private int statusCode;
    private String statusDescription;
    private String[] errorDetails;

    public double getTravelDistance() {
        return travelDistance;
    }

    public int getTravelDuration() {
        return travelDuration;
    }

    public ArrayList<String> getWaypointsOrder() {
        return waypointsOrder;
    }

    public RouteLeg getRouteLeg() {
        return routeLegs.get(0);
    }

    public List<RouteSubLeg> getRouteSubLegs() {
        return routeLegs.get(0).getRouteSubLegs();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public String[] getErrorDetails() {
        return errorDetails;
    }
}
