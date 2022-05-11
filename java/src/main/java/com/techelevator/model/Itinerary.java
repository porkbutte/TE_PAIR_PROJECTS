package com.techelevator.model;

public class Itinerary {

    private Long itineraryId;
    private String itineraryName;
    private Long userId;
    private double startingLongitude;
    private double startingLatitude;
    private String itineraryDate;



    public Itinerary(){}

    public Itinerary(Long itineraryId, String itineraryName, Long userId, double startingLongitude, double startingLatitude, String itineraryDate) {
        this.itineraryId = itineraryId;
        this.itineraryName = itineraryName;
        this.userId = userId;
        this.startingLongitude = startingLongitude;
        this.startingLatitude = startingLatitude;
        this.itineraryDate = itineraryDate;

    }



    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getStartingLongitude() {
        return startingLongitude;
    }

    public void setStartingLongitude(double startingLongitude) {
        this.startingLongitude = startingLongitude;
    }

    public double getStartingLatitude() {
        return startingLatitude;
    }

    public void setStartingLatitude(double startingLatitude) {
        this.startingLatitude = startingLatitude;
    }

    public String getItineraryDate() {
        return itineraryDate;
    }

    public void setDate(String itineraryDate) {
        this.itineraryDate = itineraryDate;
    }
}
