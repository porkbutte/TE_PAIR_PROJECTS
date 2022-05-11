package com.techelevator.controller;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    private final ReviewDao reviewDao;

    public ReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/landmarks/reviews/{id}", method = RequestMethod.GET)
    public List<Review> allTransfersForLandmark(@PathVariable long id){
        return reviewDao.findAllReviewsForLandmark(id);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/landmarks/reviews", method = RequestMethod.POST)
    public Review newReview(@RequestBody Review review){
        return reviewDao.createReview(review);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/landmarks/reviews/{id}")
    public void deleteReview(@PathVariable long id, Principal principal){
        System.out.println("This user is deleting a review: " + principal);
        reviewDao.delete(id);
    }
}
