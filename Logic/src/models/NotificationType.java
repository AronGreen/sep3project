package models;

public enum NotificationType {

    RESERVATION_PLACED("reservation", "A reservation has been placed for your trip"),
    RESERVATION_APPROVED("reservation", "Your reservation has been approved"),
    RESERVATION_REJECTED("reservation", "Your reservation has been rejected"),
    APPROVED_RESERVATION_CANCELLED_BY_PASSENGER("reservation", "A reservation for your trip has been cancelled"),

    TRIP_CANCELLED("reservation", "A trip has been cancelled that you had a reservation for"),

    INVOICE_PENDING("invoice", "An invoice has been issued that you have to pay"),
    INVOICE_CANCELLED("invoice", "Your invoice has been revoked, you don't need to pay it any more"),
    INVOICE_REFUNDED("invoice", "Your invoice has been revoked, you will soon get a refund"),
    INVOICE_REJECTED("invoice", "You have rejected to pay an invoice that you needed to pay"),

    REVIEW_CREATED_REVIEWER("review", "Review has been added"),
    REVIEW_CREATED_REVIEWEE("review","You've got a new review"),
    REVIEW_DELETED_REVIEWER("review", "Review has been deleted"),
    REVIEW_DELETED_REVIEWEE("review", "You've got a review deleted")
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
