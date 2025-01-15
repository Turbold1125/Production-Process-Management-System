package com.example.Backend.Model.Process;

import com.example.Backend.Constants.ProcessStatus;
import com.example.Backend.Model.Fibers.DoubledYarn;
import com.example.Backend.Model.Fibers.Roven;
import com.example.Backend.Model.Fibers.SingleYarn;
import com.example.Backend.Model.Fibers.WindedYarn;
import com.example.Backend.Model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "ProcessDouble")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDouble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private double available_weight;

    private double total_weight;

    @Enumerated(EnumType.STRING)
    private ProcessStatus status = ProcessStatus.NEW;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "process_double_id")
    private List<DoubledYarn> doubledYarns;
}