package com.example.ProductionManagementSystem.DTOs.Process;

import lombok.Data;

import java.util.List;

@Data
public class ProcessRequest {
    private Integer processId;
    private Integer orderId;
    private Integer inventoryId;
    private Integer userId;
    private String processName;
    private String customerName;
    private String status;
    private String inputMaterial;
    private String inputMaterialColor;
    private String OutputMaterialColor;
    private List<FiberRequest> fibers;
//    private Double totalInputWeight;
}

