package models.response;

import models.Review;

public class ReviewResponse {

    private String status;
    private Review body;

    public ReviewResponse(String status, Review body){

        this.status = status;
        this.body = body;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Review getBody() {
        return body;
    }

    public void setBody(Review body) {
        this.body = body;
    }


}
