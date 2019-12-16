package handlers;

import constants.ResponseStatus;
import dependencycollection.DependencyCollection;
import helpers.DateTimeHelper;
import helpers.StringHelper;
import models.Notification;
import models.NotificationType;
import models.Review;
import models.response.ReviewListResponse;
import models.response.ReviewResponse;
import services.INotificationService;
import services.IReviewService;
import services.ReviewService;

public class ReviewHandler implements IReviewHandler {

    private IReviewService reviewService;
    private INotificationService notificationService = DependencyCollection.getNotificationService();

    public ReviewHandler() {
        reviewService = new ReviewService();
    }

    @Override
    public ReviewResponse create(Review review) {
        if (review == null)
            return new ReviewResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        if (StringHelper.isNullOrEmpty(review.getRevieweeEmail()) ||
                StringHelper.isNullOrEmpty(review.getReviewerEmail()) ||
                StringHelper.isNullOrEmpty(review.getContent())) {
            return new ReviewResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
        return reviewService.create(review);
    }

    @Override
    public ReviewResponse delete(int id) {
        ReviewResponse response = reviewService.getById(id);

        if (response.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            return reviewService.delete(id);
        }

        return new ReviewResponse(ResponseStatus.SOCKET_NOT_FOUND, null);
    }

    @Override
    public ReviewListResponse getAllByRevieweeEmail(String revieweeEmail) {
        return reviewService.getAllByRevieweeEmail(revieweeEmail);
    }

    @Override
    public ReviewListResponse getAllByReviewerEmail(String reviewerEmail) {
        return reviewService.getAllByReviewerEmail(reviewerEmail);
    }

    @Override
    public ReviewResponse getById(int id) {
        return reviewService.getById(id);
    }
}
