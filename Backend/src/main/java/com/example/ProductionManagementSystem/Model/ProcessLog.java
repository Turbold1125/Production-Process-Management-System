package com.example.ProductionManagementSystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "process_log")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "process_name", nullable = false)
    private String processName;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "input_weight")
    private Double inputMaterialWeight;

    @Column(name = "input_material")
    private String inputMaterial;

    @Column(name = "output_weight")
    private Double outputMaterialWeight;

    @Column(name = "output_material")
    private String outputMaterial;

    @Column(name = "input_material_color")
    private String inputMaterialColor;

    @Column(name = "output_material_color")
    private String outputMaterialColor;

    @Column(name = "process_start_time")
    private LocalDateTime processStartTime;

    @Column(name = "process_end_time")
    private LocalDateTime processEndTime;

    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedTime;

    @Column(columnDefinition = "TEXT")
    private String wasteDetails;
}