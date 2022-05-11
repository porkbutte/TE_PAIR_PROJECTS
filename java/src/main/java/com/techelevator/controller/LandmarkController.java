package com.techelevator.controller;

import com.techelevator.dao.LandmarkDao;
import com.techelevator.model.Landmark;
import com.techelevator.model.LandmarkNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.techelevator.dao.LandmarkDao;
import com.techelevator.dao.UserDao;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class LandmarkController {

    private final LandmarkDao landmarkDao;
    private final UserDao userDao;

    public LandmarkController(LandmarkDao landmarkDao, UserDao userDao) {
        this.landmarkDao = landmarkDao;
        this.userDao = userDao;
    }

    @GetMapping(path = "/landmarks/{venueType}")
    public List<Landmark> showLandmarksbyVenueType(@PathVariable String venueType) throws LandmarkNotFoundException{
        return landmarkDao.showLandmarksByVenueType(venueType);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/landmarks")
    public void addLandmarkAsUser(@RequestBody Landmark landmark){
        String landmarkName = landmark.getLandmarkName();
        String description = landmark.getDescription();
        String hoo = landmark.getHoursOfOperation();
        double longitude = landmark.getLongitude();
        double latitude = landmark.getLatitude();
        boolean familyFriendly = landmark.isFamilyFriendly();
        String images = landmark.getImages();
        landmarkDao.addLandmarkAsUser(landmarkName,description,hoo,latitude, longitude,familyFriendly, images);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/landmarks/{id}")
    public void approveLandmark(@RequestBody Landmark landmark, @PathVariable long id){
        String landmarkName = landmark.getLandmarkName();
        Long venueTypeId = landmark.getVenueTypeId();
        landmarkDao.approveLandmarkAsAdmin(landmarkName, venueTypeId);
    }

    @GetMapping(path = "/landmarks/{id}/details")
    public Landmark getLandmarkDetails(@PathVariable long id) throws LandmarkNotFoundException{
        return landmarkDao.viewLandmarkDetails(id);
    }

    @GetMapping(path = "/landmarks")
    public List<Landmark> ShowApprovedLandmarks(){
        return landmarkDao.getAllApprovedLandmarks();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/itinerary/landmarks")
    public List<Landmark> showItineraryLandmarks(Principal user) throws LandmarkNotFoundException{
        long userId = userDao.findIdByUsername(user.getName());
        return landmarkDao.showLandmarksByItinerary(userId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(path = "/landmarks/{id}")
    public void addLandmarkToItinerary(@PathVariable long id, Principal user){
        long userId = userDao.findIdByUsername(user.getName());
        landmarkDao.addLandmarkToItinerary(id, userId);
    }
}
