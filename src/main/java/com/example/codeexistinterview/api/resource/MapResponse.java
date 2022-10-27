package com.example.codeexistinterview.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapResponse {

    @JsonProperty("results")
    private Result[]  results;


}
