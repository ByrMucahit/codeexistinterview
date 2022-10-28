package com.example.codeexistinterview.service.map.impl;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.api.resource.MapResponse;
import com.example.codeexistinterview.config.ApplicationProperties;
import com.example.codeexistinterview.domain.geolocation.GeoLocation;
import com.example.codeexistinterview.repositories.geolocation.GeLocationRepository;
import com.example.codeexistinterview.service.map.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final GeLocationRepository geLocationRepository;

    private final ApplicationProperties applicationProperties;

    @Override
    public MapResponse findLocationByLatLng(MapRequest mapRequest) {



        ResponseEntity<MapResponse> response = new RestTemplate()
                .getForEntity(
                        String.format("https://maps.googleapis.com/maps/api/geocode/json?latlng="
                                + mapRequest.getLatitude()
                                + ","
                                + mapRequest.getLongitude()
                                + "&key=%s", applicationProperties.getGoogleApiKey())
                        , MapResponse.class);
        return response.getBody();
    }

    private GeoLocation checkLocationSaved(double latitude, double longitude) {
        GeoLocation geoLocation = new GeoLocation();
        geLocationRepository.findByLatitudeAndLongitude(latitude, longitude)
                .ifPresent(location ->
                {
                    geoLocation.setLatitude(location.getLatitude());
                    geoLocation.setLocationName(location.getLocationName());
                    geoLocation.setLongitude(location.getLongitude());
                    });
        return geoLocation;
    }
}
