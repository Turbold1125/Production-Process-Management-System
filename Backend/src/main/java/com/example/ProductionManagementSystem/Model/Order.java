// src/main/java/com/example/Change/Model/Order.java
package com.example.ProductionManagementSystem.Model;

import com.example.ProductionManagementSystem.Model.Const.FactoryProcess;
import com.example.ProductionManagementSystem.Model.Const.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name",  nullable = false)
    private String customerName;

    @Column(name = "fiber_color",  nullable = false)
    private String fiberColor;

    @Column(name = "fiber_type",  nullable = false)
    private String fiberType;

    @Column(name = "weight",  nullable = false)
    private double weight;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "order_date",  nullable = false)
    private LocalDateTime orderDate;

    @ManyToMany
    @JoinTable(
            name = "order_2_process",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "process_id")
    )

    private List<FactoryProcess> factoryProcesses = new ArrayList<>();
}