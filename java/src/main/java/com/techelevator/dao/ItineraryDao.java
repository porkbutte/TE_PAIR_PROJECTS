package com.techelevator.dao;

import com.techelevator.exception.ItineraryNotFoundException;
import com.techelevator.model.Itinerary;

import java.util.List;

public interface ItineraryDao {

    List<Itinerary> findAllItineraries();

    Itinerary getItineraryById(Long itineraryId);

    Itinerary getItineraryByUsername(Long itineraryId, String username) throws ItineraryNotFoundException;

     Boolean createItinerary(String itineraryName, Long userId, double startingLongitude, double startingLatitude, String itineraryDate);

    void updateItinerary(Itinerary itinerary, Long itineraryId);

    void deleteItinerary(Long itineraryId);

    void deleteLandmarkFromItinerary(Long userId, Long landmarkId);
}
