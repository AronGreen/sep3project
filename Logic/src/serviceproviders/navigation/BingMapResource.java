package serviceproviders.navigation;

import java.util.ArrayList;

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

    public ArrayList<RouteLeg> getRouteLegs() {
        return routeLegs;
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

    class RouteLeg {
        private ArrayList<RouteSubLeg> routeSubLegs;

        public ArrayList<RouteSubLeg> getRouteSubLegs() {
            return routeSubLegs;
        }
    }

    class RouteSubLeg {
        private Waypoint startWaypoint;
        private Waypoint endWaypoint;
        private double travelDistance;
        private int travelDuration;

        public Waypoint getStartWaypoint() {
            return startWaypoint;
        }

        public Waypoint getEndWaypoint() {
            return endWaypoint;
        }

        public double getTravelDistance() {
            return travelDistance;
        }

        public int getTravelDuration() {
            return travelDuration;
        }
    }

    class Waypoint {
        private String description;
        private boolean isVia;
        private int routePathIndex;

        public String getDescription() {
            return description;
        }

        public boolean isVia() {
            return isVia;
        }

        public int getRoutePathIndex() {
            return routePathIndex;
        }
    }
}
