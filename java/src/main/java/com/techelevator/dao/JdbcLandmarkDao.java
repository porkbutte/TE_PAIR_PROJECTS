package com.techelevator.dao;

import com.techelevator.model.Landmark;
import com.techelevator.model.LandmarkNotFoundException;



import com.techelevator.model.VenueType;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLandmarkDao implements LandmarkDao{

    private static final Boolean UnApprovedLandmark = false;
    private static final Boolean approvedLandmark = true;
    private final JdbcTemplate jdbcTemplate;

    public JdbcLandmarkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Landmark viewLandmarkDetails(Long id) {
        String sql = "SELECT landmark_name, description, hours_of_operation, venue_type_id, images " +
                "FROM landmarks " +
                "WHERE landmark_id = ?; ";
        SqlRowSet landmark = jdbcTemplate.queryForRowSet(sql, id);
        if (landmark.next()){
            return mapRowToLandmarkDetails(landmark);
        }
        throw new LandmarkNotFoundException("Landmark not Found");
    }

    @Override
    public List<Landmark> showLandmarksByVenueType(String venueType) {
        List<Landmark> landmarksByType = new ArrayList<>();
        String sql = "SELECT * FROM landmarks " +
                "JOIN venue_type USING (venue_type_id) " +
                "WHERE venue_type ILIKE ? AND approved = true; ";
        SqlRowSet rowSetForLandmark = jdbcTemplate.queryForRowSet(sql, venueType);
        while (rowSetForLandmark.next()){
            landmarksByType.add(mapRowToLandmark(rowSetForLandmark));
        }

        return landmarksByType;
    }

    @Override
    public Boolean addLandmarkAsUser(String landmarkName, String description, String hOO, double latitude, double longitude, boolean familyFriendly, String images) {
        String sql = "INSERT INTO landmarks (landmark_name, description, hours_of_operation, latitude, longitude, family_friendly, approved, images) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";
        try {
            jdbcTemplate.update(sql, landmarkName, description, hOO, longitude, latitude, familyFriendly, UnApprovedLandmark);
        } catch (DataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean approveLandmarkAsAdmin(String landmarkName, Long venueTypeId) {
        String sql = "UPDATE landmarks " +
        "SET approved = ?, " +
        "venue_type_id =? " +
        "FROM landmarks " +
        "WHERE landmark_name ILIKE ?; ";
        try {
            jdbcTemplate.update(sql, approvedLandmark, venueTypeId, landmarkName);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public List<Landmark> showLandmarksByItinerary( Long userId) {
        List<Landmark> landmarks = new ArrayList<>();
        String sql = "SELECT * FROM landmarks " +
                "JOIN itinerary_landmark USING (landmark_id) " +
                "JOIN itinerary USING (itinerary_id) " +
                "WHERE itinerary.user_id = ?; ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while (rowSet.next()){
            landmarks.add(mapRowToLandmark(rowSet));
        }
        return landmarks;
    }

    @Override
    public List<Landmark> getAllApprovedLandmarks() {
        List<Landmark> landmarks = new ArrayList<>();
        String sql = "SELECT * FROM landmarks " +
                "WHERE approved = true; ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()){
            landmarks.add(mapRowToLandmark(rowSet));
        }
        return landmarks;
    }

    @Override
    public Boolean addLandmarkToItinerary(Long landmarkId, Long userId) {
        String sql = "INSERT INTO itinerary_landmark " +
                "VALUES ((SELECT itinerary_id FROM itinerary WHERE user_id = ?), ?); ";
        try {
            jdbcTemplate.update(sql, userId, landmarkId);
        }
        catch (DataAccessException e){
            return false;
        }
        return true;
    }


    private Landmark mapRowToLandmark(SqlRowSet rowSet){
        Landmark landmark = new Landmark();
        landmark.setLandmarkId(rowSet.getLong("landmark_id"));
        landmark.setLandmarkName(rowSet.getString("landmark_name"));
        landmark.setDescription(rowSet.getString("description"));
        landmark.setHoursOfOperation(rowSet.getString("hours_of_operation"));
        landmark.setLatitude(rowSet.getDouble("latitude"));
        landmark.setLongitude(rowSet.getDouble("longitude"));
        landmark.setVenueTypeId(rowSet.getLong("venue_type_id"));
        landmark.setFamilyFriendly(rowSet.getBoolean("family_friendly"));
        landmark.setApproved(rowSet.getBoolean("approved"));
        landmark.setImages(rowSet.getString("images"));
        return landmark;
    }

    private Landmark mapRowToLandmarkDetails(SqlRowSet rowSet){
        Landmark landmark = new Landmark();
        landmark.setLandmarkName(rowSet.getString("landmark_name"));
        landmark.setDescription(rowSet.getString("description"));
        landmark.setHoursOfOperation(rowSet.getString("hours_of_operation"));
        landmark.setVenueTypeId(rowSet.getLong("venue_type_id"));
        landmark.setImages((rowSet.getString("images")));
        return landmark;
    }

}
