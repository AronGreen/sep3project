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
        DataResponse<String> res = service.create(trip);

        return res;
    }

    @Override
    public DataResponse<String> getById(int id) {
        // No business logic needed for now
        DataResponse<String> res = service.getById(id);

        return res;
    }

    @Override
    public DataResponse<String> getFiltered(TripFilter filter) {
        // No business logic needed for now
        DataResponse<String> res = service.getFiltered(filter);

        return res;
    }
}
