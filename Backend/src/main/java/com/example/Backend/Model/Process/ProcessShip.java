package com.example.Backend.Model.Process;

import com.example.Backend.Constants.ProcessStatus;
import com.example.Backend.Model.Fibers.*;
import com.example.Backend.Model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "ProcessShip")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    private LocalDateTime dateTime;

    private double available_weight;

    private double total_weight;

    @Enumerated(EnumType.STRING)
    private ProcessStatus status = ProcessStatus.NEW;
}