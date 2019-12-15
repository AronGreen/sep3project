package models.response;

import models.Notification;

import java.util.List;

public class NotificationListResponse {

    private String status;
    private List<Notification> body;

    public NotificationListResponse(String status, List<Notification> body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public List<Notification> getBody() {
        return body;
    }
}
