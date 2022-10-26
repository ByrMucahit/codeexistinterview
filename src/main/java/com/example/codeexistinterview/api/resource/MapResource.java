package com.example.codeexistinterview.api.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MapResource {

    @JsonProperty("formatted_address")
    private String address;

    private Geometry geometry;

}
