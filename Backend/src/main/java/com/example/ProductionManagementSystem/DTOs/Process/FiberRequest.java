package com.example.ProductionManagementSystem.DTOs.Process;

import lombok.Data;

@Data
public class FiberRequest {
    private Integer inventoryId;
    private String inputMaterial;
    private Double inputMaterialWeight;
    private String processName;
}
