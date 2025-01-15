package com.example.ProductionManagementSystem.Model.Processes;

import com.example.ProductionManagementSystem.Model.Const.OutputType;
import com.example.ProductionManagementSystem.Model.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "process_output")
@NoArgsConstructor
@AllArgsConstructor
public class ProcessOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "process_id", nullable = false)
    private Integer processId;

    @Column(name = "process_name", nullable = false)
    private String processName;

    @Enumerated(EnumType.STRING)
    private OutputType type;

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

    public ProcessOutput(Integer processId, String processName, Integer orderId, Inventory inventory, OutputType outputType) {
        this.processId = processId;
        this.processName = processName;
        this.orderId = orderId;
        this.material = inventory.getFiberMaterial();
        this.weight = inventory.getConWeight();
        this.color = inventory.getFiberColor();
        this.type = outputType;
        this.customerName = inventory.getCustomerName();
        this.inventoryId = inventory.getId();
        this.dateTime = LocalDateTime.now();
    }

    public ProcessOutput(Integer processId, String processName, Integer orderId, Inventory inventory, String material, Double weight, OutputType outputType) {
        this.processId = processId;
        this.processName = processName;
        this.orderId = orderId;
        this.material = material;
        this.weight = weight;
        this.color = inventory.getFiberColor();
        this.type = outputType;
        this.customerName = inventory.getCustomerName();
        this.inventoryId = inventory.getId();
        this.dateTime = LocalDateTime.now();
    }

}
