package models.response;

import models.Review;

import java.util.List;

public class ReviewListResponse {

    private String status;
    private List<Review> body;

    public ReviewListResponse(String status, List<Review> body)
    {
        this.status = status;
        this.body = body;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Review> getBody() {
        return body;
    }

    public void setBody(List<Review> body) {
        this.body = body;
    }


}
