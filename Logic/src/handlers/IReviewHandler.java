package handlers;

import models.Review;
import models.response.ReviewListResponse;
import models.response.ReviewResponse;

public interface IReviewHandler {

    ReviewResponse create (Review review);
    ReviewResponse delete (int id);
    ReviewListResponse getAllByRevieweeEmail(String revieweeEmail);
    ReviewListResponse getAllByReviewerEmail(String reviewerEmail);
    ReviewResponse getById(int id);
}
