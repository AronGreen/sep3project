package services;

import models.Trip;
import models.TripFilter;

public interface ITripService {

    DataResponse<String> create(Trip trip);
    DataResponse<String> delete(int id);
    DataResponse<String> getById(int id);
    DataResponse<String> getFiltered(TripFilter filter);

}
