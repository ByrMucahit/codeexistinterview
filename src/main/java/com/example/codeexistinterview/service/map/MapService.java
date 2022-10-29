package com.example.codeexistinterview.service.map;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.domain.geolocation.GeoLocation;

public interface MapService {

    GeoLocation findLocationByLatLng(MapRequest mapRequest);
}
