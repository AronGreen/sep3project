package models.response;

import models.Reservation;

import java.util.List;

public class ReservationListResponse {

    private String status;
    private List<Reservation> body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getBody() {
        return body;
    }

    public void setBody(List<Reservation> body) {
        this.body = body;
    }
}
