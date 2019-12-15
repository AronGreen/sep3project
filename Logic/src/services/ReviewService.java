package services;

import helpers.JsonConverter;
import models.Review;
import models.response.ReviewListResponse;
import models.response.ReviewResponse;

public class ReviewService implements IReviewService {

    private DataConnection connection;

    public ReviewService(){connection = DataConnection.INSTANCE;}
    @Override
    public ReviewResponse create(Review review) {

        DataRequest request = new DataRequest("review","create", JsonConverter.toJson(review));

        return JsonConverter.fromJson(connection.sendRequest(request),ReviewResponse.class);
    }

    @Override
    public ReviewResponse delete(int id) {
        DataRequest request = new DataRequest("review","delete", id + "");

        return JsonConverter.fromJson(connection.sendRequest(request),ReviewResponse.class);
    }

    @Override
    public ReviewListResponse getAllByRevieweeEmail(String revieweeEmail) {
        DataRequest request = new DataRequest("review","getAllByRevieweeEmail", revieweeEmail);

        return JsonConverter.fromJson(connection.sendRequest(request),ReviewListResponse.class);
    }

    @Override
    public ReviewListResponse getAllByReviewerEmail(String reviewerEmail) {
        DataRequest request = new DataRequest("review","getAllByReviewerEmail", reviewerEmail);

        return JsonConverter.fromJson(connection.sendRequest(request),ReviewListResponse.class);
    }

    @Override
    public ReviewResponse getById(int id) {
        DataRequest request = new DataRequest("review","getById", id + "");

        return JsonConverter.fromJson(connection.sendRequest(request),ReviewResponse.class);
    }
}
