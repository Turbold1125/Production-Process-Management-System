package com.example.ProductionManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inventory_log")
@NoArgsConstructor
public class InventoryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "inventory_id", nullable = false)
    private Integer inventoryId;

    @Column(name = "customer_name",  nullable = false)
    private String customerName;

    @Column(name = "fiber_material", nullable = false)
    private String fiberMaterial;

    @Column(name = "fiber_color", nullable = false)
    private String fiberColor;

    @Column(name = "fiber_type", nullable = false)
    private String fiberType;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "rough_weight", nullable = false)
    private double roughWeight;

    @Column(name = "bale_num")
    private Integer baleNum;

    @Column(name = "bale_weight")
    private Double baleWeight;

    @Column(name = "bobbin_num")
    private Integer bobbinNum;

    @Column(name = "bobbin_weight")
    private Double bobbinWeight;

    @Column(name = "con_weight", nullable = false)
    private double conWeight;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}