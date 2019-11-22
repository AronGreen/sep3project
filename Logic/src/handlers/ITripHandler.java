package handlers;

import models.Trip;
import models.TripFilter;
import services.DataResponse;


/**
 * Responsible for performing business logic on a Trip Request
 */
public interface ITripHandler {

    DataResponse<String> create(Trip trip);
    DataResponse<String> delete(int id);
    DataResponse<String> getFiltered(TripFilter filter);
    DataResponse<String> getById(int id);

}
