package com.example.codeexistinterview.api.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoLocationResponse {

    private String locationName;

    private double longitude;

    private double latitude;
}
