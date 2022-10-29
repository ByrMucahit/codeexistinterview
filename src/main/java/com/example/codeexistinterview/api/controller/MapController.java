package com.example.codeexistinterview.api.controller;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.api.resource.GeoLocationResponse;
import com.example.codeexistinterview.api.resource.MapResponse;
import com.example.codeexistinterview.config.ApplicationProperties;
import com.example.codeexistinterview.domain.geolocation.GeoLocation;
import com.example.codeexistinterview.service.map.MapService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }


    @GetMapping("/getLocation")
    public GeoLocationResponse Location(@RequestBody MapRequest mapRequest) {
        GeoLocation location = mapService.findLocationByLatLng(mapRequest);
        return GeoLocationResponse.builder()
                .locationName(location.getLocationName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
    }
}
