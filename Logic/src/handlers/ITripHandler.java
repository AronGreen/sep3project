package handlers;

import models.Trip;
import models.TripFilter;
import services.DataResponse;


/**
 * Responsible for performing business logic on a Trip Request
 */
public interface ITripHandler {

    DataResponse create(Trip trip);
    DataResponse delete(int id);
    DataResponse getFiltered(TripFilter filter);
    DataResponse getById(int id);

}
