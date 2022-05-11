package com.techelevator.exception;

public class ItineraryNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public ItineraryNotFoundException(){
        super("Itinerary Not Found");
    }
}

