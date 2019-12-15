package serviceproviders.navigation;

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
