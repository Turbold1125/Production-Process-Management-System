package com.example.Backend.Model.Fibers;

import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "FiberReceive")
@AllArgsConstructor
@NoArgsConstructor
public class Fiber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private FiberColor color;

    private LocalDateTime dateTime;

    private int baleNum;

    private double rough_weight;
    private double bale_weight;
    private double con_weight;
    private double moisture;
}
