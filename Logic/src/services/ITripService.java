package services;

import models.Trip;
import models.TripFilter;

public interface ITripService {

    DataResponse create(Trip trip);
    DataResponse delete(int id);
    DataResponse getById(int id);
    DataResponse getFiltered(TripFilter filter);

}
