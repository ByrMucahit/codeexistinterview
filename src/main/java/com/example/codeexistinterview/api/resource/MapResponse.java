package com.example.codeexistinterview.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MapResponse {

    @JsonProperty("results")
    private List<MapResource>  mapResponse;


}
