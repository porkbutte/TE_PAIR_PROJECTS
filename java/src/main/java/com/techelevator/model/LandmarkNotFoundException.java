package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Landmark Not Found.")
public class LandmarkNotFoundException extends RuntimeException {
    public LandmarkNotFoundException(String s) {
    }
}
