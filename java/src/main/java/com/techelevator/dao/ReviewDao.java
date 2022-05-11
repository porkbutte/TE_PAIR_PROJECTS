package com.techelevator.dao;

import com.techelevator.model.Landmark;
import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {

    Review getReviewById(Long reviewId);

    List<Review> findAllReviewsForLandmark (Long landmarkId);

    Review createReview (Review review);

    void delete (long reviewId);

}
