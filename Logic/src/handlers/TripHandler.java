package handlers;

import models.Trip;
import models.TripFilter;
import services.DataResponse;
import services.TripService;

public class TripHandler implements ITripHandler {

    private TripService service;

    public TripHandler() {
        service = new TripService();
    }

    @Override
    public DataResponse<Trip> create(Trip trip) {
        // No business logic needed for now
        DataResponse<Trip> res = service.create(trip);

        return res;
    }

    @Override
    public DataResponse<Trip> getById(int id) {
        // No business logic needed for now
        DataResponse<Trip> res = service.getById(id);

        return res;
    }

    @Override
    public DataResponse<Trip[]> getFiltered(TripFilter filter) {
        // No business logic needed for now
        DataResponse<Trip[]> res = service.getFiltered(filter);

        return res;
    }
}
