package com.example.ProductionManagementSystem.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "delivery_items")
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    @JsonBackReference
    private Delivery delivery;

    @Column(name = "inventory_id", nullable = false)
    private Integer inventoryId;

    @Column(name = "material")
    private String material;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "color")
    private String color;

    @Column(name = "fiber_type")
    private String fiberType;

    @Column(name = "fiber_color")
    private String fiberColor;

    @Column(name = "delivered_time", nullable = false)
    private LocalDateTime deliveredTime;
}
