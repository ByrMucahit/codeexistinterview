package com.example.codeexistinterview.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Geometry {
    @JsonProperty("location")
    private Location location;

}
