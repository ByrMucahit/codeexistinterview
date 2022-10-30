package com.example.codeexistinterview.repositories.geolocation;

import com.example.codeexistinterview.domain.geolocation.GeoLocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface GeLocationRepository extends PagingAndSortingRepository<GeoLocation, Integer> {
    @Query("select loc from GeoLocation loc where loc.longitude = longitude and loc.latitude = latitude")
    GeoLocation findByLatitudeAndLongitude(@Param("latitude") double latitude
            , @Param("longitude") double longitude);

    boolean existsByLatitudeAndLongitude(@Param("latitude") double latitude, @Param("longitude") double longitude);
}
