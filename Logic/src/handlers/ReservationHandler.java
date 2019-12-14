package handlers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import helpers.DateTimeHelper;
import helpers.StringHelper;
import models.*;
import models.response.NotificationListResponse;
import models.response.ReservationListResponse;
import models.response.ReservationResponse;
import models.response.TripResponse;
import services.*;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationHandler implements IReservationHandler {

    private IReservationService reservationService;
    private IInvoiceHandler invoiceHandler = new InvoiceHandler();
    private ITripService tripService = new TripService();
    private INotificationService notificationService = DependencyCollection.getNotificationService();

    public ReservationHandler() {
        reservationService = new ReservationService();
    }

    @Override
    public ReservationResponse create(Reservation reservation) {
        TripResponse tripRes = tripService.getById(reservation.getTripId());
        if (!tripRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return new ReservationResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
        Trip refTrip = tripRes.getBody();
        ReservationListResponse reservationListRes = reservationService.getByTripId(refTrip.getId());
        if (!reservationListRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return new ReservationResponse(reservationListRes.getStatus(), null);
        }
        List<Reservation> reservationList = reservationListRes.getBody();
        // For now reservation allows to book only one seat.
        // This implementation should be modified when the "bookedSeats" is added to Reservation object!

        int availableSeats = refTrip.getTotalSeats();
        for (Reservation res: reservationList) {
            if (!res.getState().equals(ReservationState.REJECTED) && !res.getState().equals(ReservationState.CANCELLED))
            availableSeats--;
        }

        if(availableSeats > 0 &&
                !StringHelper.isNullOrEmpty(reservation.getDropoffAddress()) &&
                !StringHelper.isNullOrEmpty(reservation.getPickupAddress()) &&
                !StringHelper.isNullOrEmpty(reservation.getPassengerEmail())) {
            reservation.setState(ReservationState.PENDING);

            ReservationResponse res =  reservationService.create(reservation);
            reservation = res.getBody();

            // Notify driver that a reservation has been created for his trip
            if (res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
                notificationService.create(new Notification(
                        refTrip.getDriverEmail(),
                        NotificationType.RESERVATION_PLACED.getEntityType(),
                        reservation.getId(),
                        NotificationType.RESERVATION_PLACED.getMessage(),
                        DateTimeHelper.getCurrentTime()
                ));
            }

            return res;
        }

        return new ReservationResponse(ResponseStatus.SOCKET_BAD_REQUEST,null);
    }

    @Override
    public ReservationResponse update(Reservation reservation) {
        // Handle if the reservation does not exist
        ReservationResponse res = reservationService.getById(reservation.getId());
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS))
        {
            return reservationService.update(reservation);
        }
        Reservation old = res.getBody();

        // Handle if the trip the reservation refers to does not exist
        TripResponse tripRes = tripService.getById(reservation.getTripId());
        if (!tripRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return new ReservationResponse(ResponseStatus.SOCKET_INTERNAL_ERROR, null);
        }
        Trip trip = tripRes.getBody();

        // Handle if the reservation could not be updated
        ReservationResponse reservationRes = reservationService.update(reservation);
        if (!reservationRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reservationRes;
        }
        reservation = reservationRes.getBody();

        // Send notifications and invoices
        // When a pending reservation gets approved, send invoice and notify the passenger
        if (old.getState().equals(ReservationState.PENDING) && reservation.getState().equals(ReservationState.APPROVED)) {
            invoiceHandler.create(new Invoice(
                    reservation.getPassengerEmail(),
                    trip.getDriverEmail(),
                    "Reservation fee",
                    trip.getBasePrice()
                    // TODO EXTEND THIS lol
            ));

            notificationService.create(new Notification(
                    reservation.getPassengerEmail(),
                    NotificationType.RESERVATION_APPROVED.getEntityType(),
                    reservation.getId(),
                    NotificationType.RESERVATION_APPROVED.getMessage(),
                    DateTimeHelper.getCurrentTime()
            ));
        }

        // When a pending reservation gets rejected, notify the passenger
        if (old.getState().equals(ReservationState.PENDING) && reservation.getState().equals(ReservationState.REJECTED)) {
            notificationService.create(new Notification(
                    reservation.getPassengerEmail(),
                    NotificationType.RESERVATION_REJECTED.getEntityType(),
                    reservation.getId(),
                    NotificationType.RESERVATION_REJECTED.getMessage(),
                    DateTimeHelper.getCurrentTime()
            ));
        }

        return reservationService.update(reservation);
    }

    @Override
    public ReservationResponse delete(int id) {
        // Handle if the reservation does not exist
        ReservationResponse res = reservationService.getById(id);
        if (!res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reservationService.delete(id);
        }
        Reservation reservation = res.getBody();

        // Handle if the trip does not exist
        TripResponse tripRes = tripService.getById(reservation.getTripId());
        if (!tripRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reservationService.delete(id);
        }
        Trip trip = tripRes.getBody();

        // Handle if the reservation could not be deleted
        ReservationResponse reservationRes = reservationService.delete(id);
        if (!reservationRes.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reservationRes;
        }
        reservation = reservationRes.getBody();

        // Send notifications and invoices
        // If the reservation is approved, send invoice to passenger, and notify the driver
        if (reservation.getState().equals(ReservationState.APPROVED)) {
            invoiceHandler.create(new Invoice(
                    reservation.getPassengerEmail(),
                    trip.getDriverEmail(),
                    "Reservation cancellation fee",
                    trip.getCancellationFee()
            ));
            notificationService.create(new Notification(
                    trip.getDriverEmail(),
                    NotificationType.APPROVED_RESERVATION_CANCELLED_BY_PASSENGER.getMessage(),
                    reservation.getId(),
                    NotificationType.APPROVED_RESERVATION_CANCELLED_BY_PASSENGER.getMessage(),
                    DateTimeHelper.getCurrentTime()
            ));
        }

        // If the reservation is pending, delete the notification that was sent to the driver when the reservation was placed
        if (reservation.getState().equals(ReservationState.PENDING)) {
            NotificationListResponse notificationRes = notificationService.deleteAllByTypeAndItemId("reservation", reservation.getId());
        }

        return res;
    }

    @Override
    public ReservationResponse getById(int id) {
        return reservationService.getById(id);
    }

    @Override
    public ReservationListResponse getByTripId(int tripId) {
        // No logic is implemented for now

        return reservationService.getByTripId(tripId);
    }

    @Override
    public ReservationListResponse getByEmail(String email) {
        return reservationService.getByEmail(email);
    }

}
