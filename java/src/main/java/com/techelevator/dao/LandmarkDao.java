package com.techelevator.dao;

import com.techelevator.model.Landmark;
import com.techelevator.model.User;
import java.util.List;

public interface LandmarkDao {

    Landmark viewLandmarkDetails(Long id);

    List<Landmark> showLandmarksByVenueType(String venueType);

    Boolean addLandmarkAsUser(String landmarkName, String description, String hOO, double longitude, double latitude, boolean familyFriendly, String images);

    Boolean approveLandmarkAsAdmin (String landmarkName, Long venueTypeId);

    List<Landmark> showLandmarksByItinerary (Long userId);

    List<Landmark> getAllApprovedLandmarks();

    Boolean addLandmarkToItinerary(Long landmarkId, Long userId);




}
