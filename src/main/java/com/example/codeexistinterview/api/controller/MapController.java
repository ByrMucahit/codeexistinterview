package com.example.codeexistinterview.api.controller;

import com.example.codeexistinterview.api.request.MapRequest;
import com.example.codeexistinterview.api.resource.MapResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class MapController {

    /*@GetMapping("/save")
    public void ElasticSearch(@RequestBody MapRequest mapRequest) {
        // To Do
    }
*/
    @GetMapping("/getLocation")
    public MapResponse Location() {
        ResponseEntity<MapResponse> response =new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/geocode/json?new_forward_geocoder=true&address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key={API_KEY}", MapResponse.class);

        return response.getBody();
    }
}
