package com.example.Backend.DTOs;

import lombok.Data;

@Data
public class DyeingProcessRequest {
    private Long orderId;
    private double inputWeight;
    private String material;
    private String fiberType;
    private String color;
    private String customer;
}
