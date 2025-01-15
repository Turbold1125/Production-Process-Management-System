package com.example.ProductionManagementSystem.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class EndProcessRequest {
    private Integer processId;
    private Integer userId;
    private String outputMaterial;
    private Double outputWeight;
    private String outputColor;
    private String fiberType;
    private List<WasteRequest> wastes;
}
