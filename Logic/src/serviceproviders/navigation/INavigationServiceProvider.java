package serviceproviders.navigation;

import models.Reservation;
import models.Trip;
import models.TripDetails;

import java.util.List;

public interface INavigationServiceProvider {


    List<Trip> getTripsForReservation(List<Trip> trips, String pickupAddress, String dropoffAddress, double delayRate);

    /**
     * Returns the subset of the given list of trips that will not be delayed more than
     * 20% by the given reservation
     * @param trips the list of trips to filter
     * @param pickupAddress the pickup point of the reservation
     * @param dropoffAddress the dropoff point of the reservation
     * @return the subset of the given list of trips that will not be delayed more than delayRate % by the given reservation
     */
    List<Trip> getTripsForReservation(List<Trip> trips, String pickupAddress, String dropoffAddress);

    /**
     * Calculates the details of a trip for the given reservations
     * @param trip the trip to supply a start and end point to the calculation
     * @param reservations the reservations to fit between the trip start and end
     * @return the calculated trip details
     */
    TripDetails getTripDetails(Trip trip, List<Reservation> reservations);

    List<TripDetails> getAllTripDetails(List<Trip> trips, List<List<Reservation>> reservationsList);

    int getDistance(String start, String end);
}
