package com.example.ProductionManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "deliveries")
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // "ORDER" or "INVENTORY"
    @Column(name = "delivery_type", nullable = false)
    private String deliveryType;

    @Column(name = "order_id")
    private Integer orderId; // Null if it's inventory-based delivery

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "delivery_time", nullable = false)
    private LocalDateTime deliveryTime;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryItem> items = new ArrayList<>();
}
