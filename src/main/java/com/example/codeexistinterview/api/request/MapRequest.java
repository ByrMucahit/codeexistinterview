package com.example.codeexistinterview.api.request;

import lombok.Data;

@Data
public class MapRequest {
    String longitude;
    String latitude;
    String radius;
}
