package com.example.ProductionManagementSystem.DTOs.Process;

import lombok.Data;

import java.util.List;

@Data
public class EndProcessRequest {
    private Integer processId;
    private Integer userId;
    private Double outputWeight;
    private String outputColor;
    private String fiberType;
    private List<WasteRequest> wastes;
}
