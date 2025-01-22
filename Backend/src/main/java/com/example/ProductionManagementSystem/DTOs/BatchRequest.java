package com.example.ProductionManagementSystem.DTOs;

import lombok.Data;

@Data
public class BatchRequest {
    private Integer lotId;
    private double batchSize;
    private String processName;

    public BatchRequest(Integer lotId, String processName, double batchSize) {
        this.lotId = lotId;
        this.processName = processName;
        this.batchSize = batchSize;
    }

}
