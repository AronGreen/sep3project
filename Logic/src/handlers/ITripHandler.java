package handlers;

import models.Trip;
import models.TripFilter;
import models.response.TripDetailsResponse;
import models.response.TripListResponse;
import models.response.TripResponse;


/**
 * Responsible for performing business logic on a Trip Request
 */
public interface ITripHandler {

    TripResponse create(Trip trip);
    TripResponse delete(int id);
    TripListResponse getFiltered(TripFilter filter);
    TripResponse getById(int id);
    TripDetailsResponse getTripDetails(int id);

}
