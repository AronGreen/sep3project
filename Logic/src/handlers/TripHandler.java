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
    public DataResponse create(Trip trip) {
        // No business logic needed for now

        return service.create(trip);
    }

    @Override
    public DataResponse delete(int id) {
        return service.delete(id);
    }

    @Override
    public DataResponse getFiltered(TripFilter filter) {
        // No business logic needed for now

        return service.getFiltered(filter);
    }

    @Override
    public DataResponse getById(int id) {
        // No business logic needed for now

        return service.getById(id);
    }

}
