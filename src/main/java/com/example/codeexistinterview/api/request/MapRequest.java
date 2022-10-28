package com.example.codeexistinterview.api.request;

import lombok.Data;

@Data
public class MapRequest {
    private double longitude;
    private double latitude;
    private double radius;
}
