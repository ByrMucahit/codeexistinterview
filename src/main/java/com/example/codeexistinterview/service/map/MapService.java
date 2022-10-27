package com.example.codeexistinterview.service.map;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.api.resource.MapResponse;
import org.springframework.stereotype.Service;

public interface MapService {

    MapResponse findLocationByLatLng(MapRequest mapRequest);
}
