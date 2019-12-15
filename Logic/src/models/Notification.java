package models;

import helpers.DateTimeHelper;

public class Notification {

    private int id;
    private String accountEmail;
    private String type;
    private int itemId;
    private String message;
    private String date;

    public Notification(String accountEmail, String type, int itemId, String message, String date) {
        this.accountEmail = accountEmail;
        this.type = type;
        this.itemId = itemId;
        this.message = message;
        this.date = date;
    }

    public Notification(String accountEmail, NotificationType type, int itemId) {
        this.accountEmail = accountEmail;
        this.type = type.getEntityType();
        this.itemId = itemId;
        this.message = type.getMessage();
        this.date = DateTimeHelper.getCurrentTime();
    }

    public int getId() {
        return id;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public String getType() {
        return type;
    }

    public int getItemId() {
        return itemId;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
