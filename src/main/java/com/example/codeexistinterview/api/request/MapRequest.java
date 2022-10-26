package com.example.codeexistinterview.api.request;

import lombok.Data;

@Data
public class MapRequest {
    long longitude;
    long latitude;
    long radius;
}
