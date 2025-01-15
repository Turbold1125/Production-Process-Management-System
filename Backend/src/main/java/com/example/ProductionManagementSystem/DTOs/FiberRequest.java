package com.example.ProductionManagementSystem.DTOs;

import lombok.Data;

@Data
public class FiberRequest {
    private Integer inventoryId;
    private String inputMaterial;
    private Double inputMaterialWeight;
}
