package com.techelevator.model;

public class Landmark {

    private Long landmarkId;
    private String landmarkName;
    private String description;
    private String hoursOfOperation;
    private double longitude;
    private double latitude;
    private Long venueTypeId;
    private boolean familyFriendly;
    private boolean approved;
    public String images;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(Long landmarkId) {
        this.landmarkId = landmarkId;
    }

    public Long getVenueTypeId() {
        return venueTypeId;
    }

    public void setVenueTypeId(Long venueTypeId) {
        this.venueTypeId = venueTypeId;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isFamilyFriendly() {
        return familyFriendly;
    }

    public void setFamilyFriendly(boolean familyFriendly) {
        this.familyFriendly = familyFriendly;
    }

    public String getImages() {return images;}

    public void setImages(String images) {this.images = images;}

    public Landmark(){}

    public Landmark(Long landmarkId, String landmarkName, String description, String hoursOfOperation, double longitude, double latitude, Long venueTypeId, boolean familyFriendly, boolean approved, String images) {
        this.landmarkId = landmarkId;
        this.landmarkName = landmarkName;
        this.description = description;
        this.hoursOfOperation = hoursOfOperation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.venueTypeId = venueTypeId;
        this.familyFriendly = familyFriendly;
        this.approved = approved;
        this.images = images;
    }

}
