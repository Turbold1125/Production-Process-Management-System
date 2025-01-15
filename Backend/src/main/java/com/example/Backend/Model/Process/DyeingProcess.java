package com.example.Backend.Model.Process;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DyeingProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String material;
    private String fiberType;
    private String color;
    private String customer;
    private double inputWeight;
    private double outputWeight;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}