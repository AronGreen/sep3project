package serviceproviders.navigation;

import models.Reservation;
import models.Trip;
import models.TripDetails;

import java.util.List;

public interface INavigationServiceProvider {
    /**
     * Returns the subset of the given list of trips that will not be delayed more than
     * delayRate% by the given reservation
     * @param trips the list of trips to filter
     * @param reservation the reservation to check against
     * @param delayRate the maximum delay allowed given as a percentage
     * @return the subset of the given list of trips that will not be delayed more than delayRate % by the given reservation
     */
    List<Trip> getTripsForReservation(List<Trip> trips, Reservation reservation, double delayRate);

    /**
     * Returns the subset of the given list of trips that will not be delayed more than
     * 10% by the given reservation
     * @param trips the list of trips to filter
     * @param reservation the reservation to check against
     * @return the subset of the given list of trips that will not be delayed more than delayRate % by the given reservation
     */
    List<Trip> getTripsForReservation(List<Trip> trips, Reservation reservation);

    /**
     * Calculates the details of a trip for the given reservations
     * @param trip the trip to supply a start and end point to the calculation
     * @param reservations the reservations to fit between the trip start and end
     * @return the calculated trip details
     */
    TripDetails getTripDetails(Trip trip, List<Reservation> reservations);
}
