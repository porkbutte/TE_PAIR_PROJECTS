package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "Venue Type Not Found.")
public class VenueTypeNotFoundException extends RuntimeException {

}
