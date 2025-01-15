package com.example.ProductionManagementSystem.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class DeliveryRequest {
    private String deliveryType;
    private String customerName;
    private List<Integer> inventoryIds;
    private String address;
    private Boolean deliverAll;
}
