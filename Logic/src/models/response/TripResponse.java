package models.response;

import models.Trip;

public class TripResponse {

    private String status;
    private Trip body;

    public TripResponse(String status, Trip body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trip getBody() {
        return body;
    }

    public void setBody(Trip body) {
        this.body = body;
    }
}
