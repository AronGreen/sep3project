package models;

public enum NotificationType {

    RESERVATION_PLACED("reservation", "A reservation has been placed for your trip"),
    RESERVATION_APPROVED("reservation", "Your reservation has been approved"),
    RESERVATION_REJECTED("reservation", "Your reservation has been rejected"),
    APPROVED_RESERVATION_CANCELLED_BY_PASSENGER("reservation", "A reservation for your trip has been cancelled")
    ;

    private String entityType;
    private String message;

    NotificationType(String entityType, String message) {
        this.entityType = entityType;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getEntityType() {
        return entityType;
    }
}
