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
    public DataResponse<String> create(Trip trip) {
        // No business logic needed for now

        return service.create(trip);
    }

    @Override
    public DataResponse<String> delete(int id) {
        return service.delete(id);
    }

    @Override
    public DataResponse<String> getFiltered(TripFilter filter) {
        // No business logic needed for now

        return service.getFiltered(filter);
    }

    @Override
    public DataResponse<String> getById(int id) {
        // No business logic needed for now

        return service.getById(id);
    }

}
