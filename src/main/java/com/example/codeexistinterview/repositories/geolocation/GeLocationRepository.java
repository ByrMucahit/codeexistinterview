package com.example.codeexistinterview.repositories.geolocation;

import com.example.codeexistinterview.domain.geolocation.GeoLocation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GeLocationRepository extends PagingAndSortingRepository<GeoLocation, Integer> {
    Optional<GeoLocation> findByLatitudeAndLongitude(@Param("latitude") double latitude
            , @Param("longitude") double longitude);
}
