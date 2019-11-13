package services;

import models.Trip;
import models.TripFilter;

public interface ITripService {

    DataResponse<Trip> create(Trip trip);
    DataResponse<Trip> delete(int id);
    DataResponse<Trip> getById(int id);
    DataResponse<Trip[]> getFiltered(TripFilter filter);

}
