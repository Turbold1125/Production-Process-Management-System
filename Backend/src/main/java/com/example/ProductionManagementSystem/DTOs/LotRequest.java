package com.example.ProductionManagementSystem.DTOs;

import lombok.Data;

@Data
public class LotRequest {
    private Integer orderId;
    private String materialName;
    private double lotWeight;
}
