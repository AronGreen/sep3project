package models;

public class Review {
    private int id;
    private String reviewerEmail;
    private String revieweeEmail;
    private String content;

    public Review(int id, String reviewerEmail, String revieweeEmail, String content)

    {
        this.id = id;
        this.revieweeEmail = revieweeEmail;
        this.reviewerEmail = reviewerEmail;
        this.content = content;


    }

    public int getId() {
        return id;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public String getRevieweeEmail() {
        return revieweeEmail;
    }

    public String getContent() {
        return content;
    }
}
