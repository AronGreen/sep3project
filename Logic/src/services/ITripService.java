package services;

import models.Trip;
import models.TripFilter;
import models.response.TripListResponse;
import models.response.TripResponse;

public interface ITripService {

    TripResponse create(Trip trip);
    TripResponse delete(int id);
    TripResponse getById(int id);
    TripListResponse getFiltered(TripFilter filter);

}
