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
        if (!StringHelper.isNullOrEmpty(review.getRevieweeEmail()) &&
                !StringHelper.isNullOrEmpty(review.getReviewerEmail()) &&
                !StringHelper.isNullOrEmpty(review.getContent())) {
            return new ReviewResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
        ReviewResponse res =  reviewService.create(review);
        review = res.getBody();
        // Notify the reviewer and reviewee about the review
       if (res.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            notificationService.create(new Notification(
                   review.getReviewerEmail(),
                   NotificationType.REVIEW_CREATED_REVIEWER.getEntityType(),
                   review.getId(),
                   NotificationType.REVIEW_CREATED_REVIEWER.getMessage(),
                   DateTimeHelper.getCurrentTime()));

           notificationService.create(new Notification(
                   review.getRevieweeEmail(),
                   NotificationType.REVIEW_CREATED_REVIEWEE.getEntityType(),
                   review.getId(),
                   NotificationType.REVIEW_CREATED_REVIEWEE.getMessage(),
                   DateTimeHelper.getCurrentTime()));
        }
        return res;

    }

    @Override
    public ReviewResponse delete(int id) {
        ReviewResponse response = reviewService.getById(id);

            // Delete and Notify the reviewer and reviewee
        if (response.getStatus().equals(ResponseStatus.SOCKET_SUCCESS)) {
            notificationService.create(new Notification(
                    response.getBody().getReviewerEmail(),
                    NotificationType.REVIEW_DELETED_REVIEWER.getEntityType(),
                    response.getBody().getId(),
                    NotificationType.REVIEW_DELETED_REVIEWER.getMessage(),
                    DateTimeHelper.getCurrentTime()));

            notificationService.create(new Notification(
                    response.getBody().getRevieweeEmail(),
                    NotificationType.REVIEW_DELETED_REVIEWEE.getEntityType(),
                    response.getBody().getId(),
                    NotificationType.REVIEW_DELETED_REVIEWEE.getMessage(),
                    DateTimeHelper.getCurrentTime()));
            return reviewService.delete(id);
        }
        return new ReviewResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);

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
