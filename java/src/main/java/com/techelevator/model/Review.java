package com.techelevator.model;

public class Review {

    private long reviewId;
    private long landmarkId;
    private boolean rating;
    private long userId;

    public Review () {}

    public Review(long reviewId, long landmarkId, boolean rating, long userId) {
        this.reviewId = reviewId;
        this.landmarkId = landmarkId;
        this.rating = rating;
        this.userId = userId;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public long getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(long landmarkId) {
        this.landmarkId = landmarkId;
    }

    public boolean isRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", landmarkId=" + landmarkId +
                ", rating=" + rating +
                ", userId=" + userId +
                '}';
    }
}
