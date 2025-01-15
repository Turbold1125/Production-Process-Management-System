package com.example.Backend.DTOs;

import com.example.Backend.Model.Const.FactoryProcess;
import com.example.Backend.Model.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private double weight;
    private String customerName;
    private String colorName;
    private String typeName;
    private LocalDateTime orderDate;
    private List<FactoryProcess> factoryProcesses;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.weight = order.getWeight();
        this.customerName = order.getCustomer().getName();
        this.colorName = order.getColor().getName();
        this.typeName = order.getFiberType().getName();
        this.orderDate = order.getOrderDate();
        this.factoryProcesses = order.getFactoryProcesses();
    }
}
