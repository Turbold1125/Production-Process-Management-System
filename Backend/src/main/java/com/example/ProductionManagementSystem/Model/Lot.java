package com.example.ProductionManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lot")
@NoArgsConstructor
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "lot_name", nullable = false, unique = true)
    private String lotName;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "weight", nullable = false)
    private double lotWeight;

    @Column(name = "process_name", nullable = false)
    private String processName;
}
