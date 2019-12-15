package serviceproviders.navigation;

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
