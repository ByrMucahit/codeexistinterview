package com.example.codeexistinterview.api.controller;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.api.resource.GeoLocationResponse;
import com.example.codeexistinterview.domain.geolocation.GeoLocation;
import com.example.codeexistinterview.service.map.MapService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping("/getLocation")
    public GeoLocationResponse Location(@RequestBody MapRequest mapRequest) {
        GeoLocation location = mapService.findLocationByLatLng(mapRequest);
        return GeoLocationResponse.builder()
                .locationName(location.getLocationName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
    }
}
