package com.example.codeexistinterview.exception;

public class PlaceIsNotFoundException extends RuntimeException{
    public PlaceIsNotFoundException(double lng, double lat) {
        super(String.format("Lat: %f Lng: %f is not found.", lat, lng));
    }
}
