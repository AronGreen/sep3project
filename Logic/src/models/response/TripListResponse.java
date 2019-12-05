package models.response;

import models.Trip;

import java.util.List;

public class TripListResponse {

    private String status;
    private List<Trip> body;

    public TripListResponse(String status, List<Trip> body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Trip> getBody() {
        return body;
    }

    public void setBody(List<Trip> body) {
        this.body = body;
    }
}
