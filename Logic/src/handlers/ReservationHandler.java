package handlers;

import constants.ResponseStatus;
import helpers.StringHelper;
import models.Reservation;
import models.Trip;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;
import services.IReservationService;
import services.ITripService;
import services.ReservationService;
import services.TripService;

import java.util.List;

public class ReservationHandler implements IReservationHandler {

    private IReservationService service;
    private ITripService tripService;

    public ReservationHandler() {
        service = new ReservationService();
        tripService = new TripService();
    }

    @Override
    public ReservationResponse create(Reservation reservation) {
        // No logic is implemented for now

        Trip refTrip = tripService.getById(reservation.getTripId()).getBody();
        List<Reservation> reservationList = service.getByTripId(refTrip.getId()).getBody();

        // For now reservation allows to book only one seat.
        // This implementation should be modified when the "bookedSeats" is added to Reservation object!

        int availableSeats = refTrip.getTotalSeats();
        for (Reservation res : reservationList) {
            availableSeats--;
        }

        if (availableSeats > 0 &&
                StringHelper.isNullOrEmpty(reservation.getDropoffAddress()) &&
                StringHelper.isNullOrEmpty(reservation.getPickupAddress()) &&
                StringHelper.isNullOrEmpty(reservation.getPickupTime()) &&
                StringHelper.isNullOrEmpty(reservation.getPassengerEmail())) {
            return service.create(reservation);
        }

        return new ReservationResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);

    }

    @Override
    public ReservationResponse update(Reservation reservation) {
        if (StringHelper.isNullOrEmpty(reservation.getDropoffAddress()) &&
                StringHelper.isNullOrEmpty(reservation.getPickupAddress()) &&
                StringHelper.isNullOrEmpty(reservation.getPickupTime())) {

            return service.update(reservation);
        } else {
            return new ReservationResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
    }

    @Override
    public ReservationResponse delete(int id) {
        return service.delete(id);
    }

    @Override
    public ReservationResponse getById(int id) {
        return service.getById(id);
    }

    @Override
    public ReservationListResponse getByTripId(int tripId) {


        return service.getByTripId(tripId);
    }

    @Override
    public ReservationListResponse getByEmail(String email) {
        return service.getByEmail(email);
    }
}
