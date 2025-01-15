package com.example.ProductionManagementSystem.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class DeliveryResponse {
    private List<Integer> deliveredInventoryIds;
    private Integer deliveryId;
}
