package com.example.codeexistinterview.api.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Result {

    @JsonProperty("formatted_address")
    private String address;

    private Geometry geometry;

}
