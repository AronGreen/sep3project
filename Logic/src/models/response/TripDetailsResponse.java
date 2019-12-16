package models.response;

import models.TripDetails;

public class TripDetailsResponse {

    private String status;
    private TripDetails body;

    public TripDetailsResponse(String status, TripDetails body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public TripDetails getBody() {
        return body;
    }
}
