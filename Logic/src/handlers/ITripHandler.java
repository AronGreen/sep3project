package handlers;

import models.Trip;
import models.TripFilter;
import services.DataResponse;


/**
 * Responsible for performing business logic on a Trip Request
 */
public interface ITripHandler {

    DataResponse<Trip> create(Trip trip);
    DataResponse<Trip> getById(int id);
    DataResponse<Trip[]> getFiltered(TripFilter filter);

}
