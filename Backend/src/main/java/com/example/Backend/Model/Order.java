// src/main/java/com/example/Backend/Model/Process/Order.java
package com.example.Backend.Model;

import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Model.Const.FiberType;
import com.example.Backend.Model.Const.FactoryProcess;
import com.example.Backend.Model.Const.Status;
import com.example.Backend.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false, referencedColumnName = "id")
    private FiberColor color;

    @ManyToOne
    @JoinColumn(name = "fiber_type_id", nullable = false, referencedColumnName = "id")
    private FiberType fiberType;

    private double weight;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime orderDate;

    @ManyToMany
    @JoinTable(
            name = "order_2_process",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "process_id")
    )

    private List<FactoryProcess> factoryProcesses = new ArrayList<>();;

    public Order(Long id) {
        this.id = id;
    }
}