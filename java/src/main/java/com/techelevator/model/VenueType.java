package com.techelevator.model;

public class VenueType {

    private Long venueTypeId;
    private String venueType;

    public VenueType(){};

    public VenueType(Long venueTypeId, String venueType) {
        this.venueTypeId = venueTypeId;
        this.venueType = venueType;
    }

    public Long getVenueTypeId() {
        return venueTypeId;
    }

    public void setVenueTypeId(Long venueTypeId) {
        this.venueTypeId = venueTypeId;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }
}
