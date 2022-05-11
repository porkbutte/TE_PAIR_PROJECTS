package com.techelevator.dao;

import com.techelevator.model.Landmark;
import com.techelevator.model.Review;
import org.openqa.selenium.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        String sql = "select * from reviews where reviewId = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reviewId);
        if(results.next()){
            return mapRowToReview(results);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Review> findAllReviewsForLandmark(Long landmarkId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "select * from reviews where landmarkId = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landmarkId);
        while(results.next()){
            Review review = mapRowToReview(results);
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public Review createReview(Review review) {
        String sql = "insert into reviews (landmarkId, rating, userId)" +
                "values (?, ?, ?)";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, review.getLandmarkId(),
                review.isRating(), review.getUserId());

        return getReviewById(newId);
    }

    @Override
    public void delete(long reviewId) {
        String sql = "select reviewId from reviews where reviewId = ?";
        jdbcTemplate.update(sql, reviewId);
    }

    private Review mapRowToReview (SqlRowSet rs){
        Review review = new Review();
        review.setReviewId(rs.getLong("review_id"));
        review.setLandmarkId(rs.getLong("landmark_id"));
        review.setRating(rs.getBoolean("rating"));
        review.setUserId(rs.getLong("user_id"));
        return review;
    }

}
