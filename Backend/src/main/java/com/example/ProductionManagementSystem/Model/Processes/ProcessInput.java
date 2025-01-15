package com.example.ProductionManagementSystem.Model.Processes;

import com.example.ProductionManagementSystem.DTOs.FiberRequest;
import com.example.ProductionManagementSystem.Model.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "process_inputs")
@NoArgsConstructor
@AllArgsConstructor
public class ProcessInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "process_id", nullable = false)
    private Integer processId;

    @Column(name = "process_name", nullable = false)
    private String processName;

    @Column(name = "material")
    private String material;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "color")
    private String color;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "order_id")
    private Integer orderId;

    public ProcessInput(Integer processId, String processName, Integer orderId, FiberRequest fiber, Inventory inventory, String customerName) {
        this.processId = processId;
        this.processName = processName;
        this.orderId = orderId;
        this.weight = fiber.getInputMaterialWeight();
        this.material = fiber.getInputMaterial();
        this.color = inventory.getFiberColor();
        this.inventoryId = fiber.getInventoryId();
        this.customerName = customerName;
        this.dateTime = inventory.getDateTime();
    }
}

