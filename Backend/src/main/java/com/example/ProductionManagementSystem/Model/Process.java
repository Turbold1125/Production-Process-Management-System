package com.example.ProductionManagementSystem.Model;

import com.example.ProductionManagementSystem.Constants.ProcessStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Process")
@NoArgsConstructor
@AllArgsConstructor
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "process_name", nullable = false)
    private String processName;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Enumerated(EnumType.STRING)
    private ProcessStatus status = ProcessStatus.NEW;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "input_material")
    private String inputMaterial;

    @Column(name = "input_material_color")
    private String inputMaterialColor;

    @Column(name = "input_material_weight")
    private Double inputMaterialWeight;

    @Column(name = "output_material")
    private String outputMaterial;

    @Column(name = "output_material_color")
    private String outputMaterialColor;

    @Column(name = "output_material_weight")
    private Double outputMaterialWeight;
}
