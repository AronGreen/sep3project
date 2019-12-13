package models.response;

import models.Reservation;

public class ReservationResponse {

    private String status;
    private Reservation body;
    public ReservationResponse(String status,Reservation body){
        this.body = body;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reservation getBody() {
        return body;
    }

    public void setBody(Reservation body) {
        this.body = body;
    }
}
