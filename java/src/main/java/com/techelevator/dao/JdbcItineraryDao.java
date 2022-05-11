package com.techelevator.dao;

import com.techelevator.exception.ItineraryNotFoundException;
import com.techelevator.model.Itinerary;

import com.techelevator.model.UserNotFoundException;
import org.openqa.selenium.NotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;



@Component
public class JdbcItineraryDao implements ItineraryDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcItineraryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Itinerary> findAllItineraries() {
        List<Itinerary> itineraries = new ArrayList<>();
        String sql = "SELECT * FROM itinerary; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            itineraries.add(mapRowToItineraries(results));
        }
        return itineraries;
    }

    @Override
    public Itinerary getItineraryById(Long itineraryId) {
        String sql = "SELECT * FROM itinerary WHERE itinerary_id =?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itineraryId);
        if (results.next()){
            return mapRowToItineraries(results);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Itinerary getItineraryByUsername(Long itineraryId, String username) throws ItineraryNotFoundException {
        String sql = "SELECT itinerary.*, users.username " +
                "FROM itinerary " +
                "JOIN users USING (user_id) " +
                "WHERE itinerary_id = ? AND username = ?; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itineraryId, username);
        if (results.next()){
            return mapRowToItineraries(results);
        } else {
            throw new UserNotFoundException();
        }
    }



    @Override
    public Boolean createItinerary(String itineraryName, Long userId, double startingLongitude, double startingLatitude, String itineraryDate) {



        String sql = "INSERT INTO itinerary (itinerary_name, user_id, starting_longitude, starting_latitude, itinerary_date) " +
                "VALUES (?, ?, ?, ?, ?); " ;


    try{    jdbcTemplate.update(sql, itineraryName, userId, startingLongitude, startingLatitude, itineraryDate);
    }
    catch (DataAccessException e){


        return false;
    } return true;}

    @Override
    public void updateItinerary(Itinerary itinerary, Long itineraryId) {
        String sql = "update itinerary set itinerary_name = ?, user_id = ?, starting_longitude = ?, " +
                "starting_latitude = ?, itinerary_date = ? where itinerary_id = ?; ";
        jdbcTemplate.update(sql, itinerary.getItineraryName(), itinerary.getUserId(), itinerary.getStartingLongitude(),
                itinerary.getStartingLatitude(), itinerary.getItineraryDate(), itineraryId);
    }

    @Override
    public void deleteItinerary(Long itineraryId) {
        String sql = "delete from itinerary where user_id = ?; ";
        jdbcTemplate.update(sql, itineraryId);
    }

    @Override
    public void deleteLandmarkFromItinerary(Long userId, Long landmarkId) {
        String sql = ("delete from itinerary_landmark where itinerary_id = " +
                "(select itinerary_id from itinerary where user_id = ?) " +
                "AND landmark_id = ?;");
        jdbcTemplate.update(sql, userId ,landmarkId);
    }

    private Itinerary mapRowToItineraries(SqlRowSet rs){
        Itinerary itinerary = new Itinerary();

        itinerary.setItineraryId(rs.getLong("itinerary_id"));
        itinerary.setItineraryName(rs.getString("itinerary_name"));
        itinerary.setUserId(rs.getLong("user_id"));
        itinerary.setStartingLongitude(rs.getDouble("starting_longitude"));
        itinerary.setStartingLatitude(rs.getDouble("starting_latitude"));
        itinerary.setDate(rs.getString("itinerary_date"));

        return itinerary;
    }

    @Override
    public String toString() {
        return "JdbcItineraryDao{" +
                "jdbcTemplate=" + jdbcTemplate +
                '}';
    }
}
