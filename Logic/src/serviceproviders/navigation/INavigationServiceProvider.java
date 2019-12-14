package serviceproviders.navigation;

import models.Reservation;
import models.Trip;
import models.TripDetails;

import java.util.List;

public interface INavigationServiceProvider {
    List<Trip> getTripsForReservation(List<Trip> trips, Reservation reservation, double delayRate);
    List<Trip> getTripsForReservation(List<Trip> trips, Reservation reservation);
    TripDetails getTripDetails(Trip trip, List<Reservation> reservations);
}
