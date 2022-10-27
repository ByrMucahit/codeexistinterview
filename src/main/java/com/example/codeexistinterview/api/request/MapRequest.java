package com.example.codeexistinterview.api.request;

import lombok.Data;

@Data
public class MapRequest {
    private String longitude;
    private String latitude;
    private String radius;
}
