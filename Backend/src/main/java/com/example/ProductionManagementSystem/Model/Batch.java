package com.example.ProductionManagementSystem.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "batch")
@NoArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lot_name", nullable = false)
    private String lotName;

    @Column(name = "batch_name", nullable = false, unique = true)
    private String batchName;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "lot_id", nullable = false)
    private Integer lotId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "process_name", nullable = false)
    private String processName;
}
