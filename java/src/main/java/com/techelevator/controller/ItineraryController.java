package com.techelevator.controller;


import com.techelevator.dao.ItineraryDao;
import com.techelevator.exception.ItineraryNotFoundException;
import com.techelevator.model.Itinerary;
import com.techelevator.model.LandmarkNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.techelevator.dao.UserDao;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ItineraryController {

    @Autowired
    public ItineraryDao itineraryDao;
    public UserDao userDao;

    public ItineraryController(ItineraryDao itineraryDao, UserDao userDao) {
        this.itineraryDao = itineraryDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/itineraries", method = RequestMethod.GET)
    public List<Itinerary> findAllItineraries(){
        return itineraryDao.findAllItineraries();
    }

    @RequestMapping(path = "/itinerary/{itineraryId}", method = RequestMethod.GET)
    public Itinerary getItineraryById(@PathVariable Long itineraryId) throws ItineraryNotFoundException {
        return itineraryDao.getItineraryById(itineraryId);
    }

    @RequestMapping(path = "/users/itinerary/{itineraryId}", method = RequestMethod.GET)
    public Itinerary getItineraryByUsername(@PathVariable Long itineraryId, String username) throws ItineraryNotFoundException {

        return itineraryDao.getItineraryByUsername(itineraryId, username);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping( path = "/itinerary")
    @Transactional
    public void createItinerary(@RequestBody Itinerary itinerary, Principal user) throws ItineraryNotFoundException {

        String itineraryName= itinerary.getItineraryName();
        long userId = userDao.findIdByUsername(user.getName());//itinerary.getUserId();
        double startingLongitude = itinerary.getStartingLongitude();
        double startingLatitude = itinerary.getStartingLatitude();
        String itineraryDate = itinerary.getItineraryDate();

        itineraryDao.createItinerary(itineraryName,  userId, startingLongitude,startingLatitude,itineraryDate);
    }

    @RequestMapping(path = "/itinerary/{itineraryId}", method = RequestMethod.PUT)
    public void updateItinerary(@RequestBody Itinerary itinerary, Principal principal) throws ItineraryNotFoundException {
        int userId= userDao.findIdByUsername(principal.getName());
        itineraryDao.updateItinerary(itinerary,(long) userId);
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/itinerary", method = RequestMethod.DELETE)
    public void deleteItinerary(Principal principal) throws ItineraryNotFoundException {
    int userId=  userDao.findIdByUsername(principal.getName());
        itineraryDao.deleteItinerary((long) userId);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/landmarks/{landmarkId}", method = RequestMethod.DELETE)
    public void deleteLandmarkFromItinerary(@PathVariable Long landmarkId, Long userId) throws LandmarkNotFoundException {
        itineraryDao.deleteLandmarkFromItinerary(userId, landmarkId);
    }


}

