package com.example.ProductionManagementSystem.DTOs;

import com.example.ProductionManagementSystem.Model.Const.FactoryProcess;
import com.example.ProductionManagementSystem.Model.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Integer id;
    private double weight;
    private String customerName;
    private String fiberColor;
    private String fiberType;
    private String status;
    private LocalDateTime orderDate;
    private List<FactoryProcess> factoryProcesses;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.weight = order.getWeight();
        this.customerName= order.getCustomerName();
        this.fiberColor = order.getFiberColor();
        this.fiberType = order.getFiberType();
        this.status = order.getStatus().name();
        this.orderDate = order.getOrderDate();
        this.factoryProcesses = order.getFactoryProcesses();
    }
}
