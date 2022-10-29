package com.example.codeexistinterview.service.map.impl;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.api.resource.MapResponse;
import com.example.codeexistinterview.api.resource.Result;
import com.example.codeexistinterview.config.ApplicationProperties;
import com.example.codeexistinterview.domain.geolocation.GeoLocation;
import com.example.codeexistinterview.exception.GlobalExceptionHandler;
import com.example.codeexistinterview.exception.PlaceIsNotFoundException;
import com.example.codeexistinterview.repositories.geolocation.GeLocationRepository;
import com.example.codeexistinterview.service.map.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapServiceImpl implements MapService {

    private final GeLocationRepository geLocationRepository;

    private final ApplicationProperties applicationProperties;

    private boolean checkLocationSaved(double latitude, double longitude) {
        return geLocationRepository.existsAllByLatitudeAndLongitude(latitude, longitude) ;
    }

    private void checkLongitudeLatitudeValue(MapRequest mapRequest) {
        log.info("Checks Values User has entered");
        if(mapRequest.getLatitude() > 180.00 || mapRequest.getLatitude() < -180.00) {
            log.error("The value you have entered is not legal");
            throw new PlaceIsNotFoundException(mapRequest.getLongitude(), mapRequest.getLatitude());
        }
        if(mapRequest.getLongitude() > 180.00 || mapRequest.getLongitude() < -180.00) {
            log.error("The value you have entered is not legal");
            throw new PlaceIsNotFoundException(mapRequest.getLongitude(), mapRequest.getLatitude());
        }
    }

    @Override
    public GeoLocation findLocationByLatLng(MapRequest mapRequest) {
        log.info("Transaction starting...");

        this.checkLongitudeLatitudeValue(mapRequest);



        if (checkLocationSaved(Double.valueOf(mapRequest.getLatitude()), Double.valueOf(mapRequest.getLongitude()))) {
            log.info("The result was saved before...");
            return findLocationFromDb(mapRequest);
        } else {
            Result response  = findLocationFromGoogleApi(mapRequest);
            return GeoLocation.builder()
                    .locationName(response.getAddress())
                    .latitude(response.getGeometry().getLocation().getLat())
                    .longitude(response.getGeometry().getLocation().getLng()).build();
        }

    }

    private GeoLocation findLocationFromDb(MapRequest mapRequest) {
        var location = geLocationRepository.findByLatitudeAndLongitude(mapRequest.getLatitude()
                , mapRequest.getLongitude());
        log.info("get info from db...");
        return GeoLocation.builder()
                .locationName(location.getLocationName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();

    }

    private Result findLocationFromGoogleApi(MapRequest mapRequest) {
        try {
            ResponseEntity<MapResponse> response = new RestTemplate()
                    .getForEntity(
                            String.format("https://maps.googleapis.com/maps/api/geocode/json?latlng="
                                    + mapRequest.getLatitude()
                                    + ","
                                    + mapRequest.getLongitude()
                                    + "&key=%s", applicationProperties.getGoogleApiKey())
                            , MapResponse.class);

            this.saveLocation(response.getBody().getResults()[0]);

            return response.getBody().getResults()[0];
        }
        catch (Exception exception) {
            throw new RuntimeException("There is no place");
        }
    }

    private void saveLocation(Result result) {
        geLocationRepository.save(GeoLocation.builder()
                .locationName(result.getAddress())
                .longitude(result.getGeometry().getLocation().getLng())
                .latitude(result.getGeometry().getLocation().getLat()).build());
    }
}
