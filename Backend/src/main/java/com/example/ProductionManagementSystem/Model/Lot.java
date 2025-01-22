package com.example.ProductionManagementSystem.Model;

import com.example.ProductionManagementSystem.Model.Const.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "weight", nullable = false)
    private double lotWeight;

    @Column(name = "remaining_weight")
    private double remainingWeight;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "material_name", nullable = false)
    private String materialName;

    @Transient
    private List<Batch> batches = new ArrayList<>();
}



