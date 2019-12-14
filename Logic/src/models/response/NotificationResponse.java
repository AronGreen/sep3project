package models.response;

import models.Notification;

public class NotificationResponse {

    private String status;
    private Notification body;

    public NotificationResponse(String status, Notification body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public Notification getBody() {
        return body;
    }
}
