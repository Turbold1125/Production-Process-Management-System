package com.example.Backend.Model.Fibers;

import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Model.Const.FiberType;
import com.example.Backend.Model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "SingleYarn")
@AllArgsConstructor
@NoArgsConstructor
public class SingleYarn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fiber_type", referencedColumnName = "id", nullable = false)
    private FiberType fiberType;

    @ManyToOne
    @JoinColumn(name = "out_color", referencedColumnName = "id", nullable = false)
    private FiberColor color;

    private LocalDateTime dateTime;

    private int lotNum;

    private int bobbinNum;

    private double rough_weight;
    private double bobbin_weight;
    private double con_weight;
    private double moisture;
}

