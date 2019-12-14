package serviceproviders.navigation;

import models.Reservation;
import models.Trip;
import models.TripDetails;

import java.util.List;

public interface INavigationServiceProvider {
    List<Trip> getTripsForPickupPoint(List<Trip> trips, String pickupPoint, double delayRate);
    List<Trip> getTripsForPickupPoint(List<Trip> trips, String pickupPoint);
    TripDetails getTripDetails(Trip trip, List<Reservation> reservations);
}
