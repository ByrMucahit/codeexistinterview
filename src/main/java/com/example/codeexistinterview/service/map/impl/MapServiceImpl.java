package com.example.codeexistinterview.service.map.impl;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.api.resource.MapResponse;
import com.example.codeexistinterview.config.ApplicationProperties;
import com.example.codeexistinterview.service.map.MapService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final ApplicationProperties applicationProperties;

    @Override
    public MapResponse findLocationByLatLng(MapRequest mapRequest) {
        var b = applicationProperties.getGoogleApiKey();
        ResponseEntity<MapResponse> response =new RestTemplate()
                .getForEntity(String.format("https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=%s", applicationProperties.getGoogleApiKey()), MapResponse.class);
        return response.getBody();
    }
}
