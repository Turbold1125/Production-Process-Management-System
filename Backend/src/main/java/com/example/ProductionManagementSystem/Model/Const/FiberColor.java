// src/main/java/com/example/Change/Model/Const/FiberColor.java
package com.example.ProductionManagementSystem.Model.Const;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "fiber_color")
@NoArgsConstructor
public class FiberColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "name_en", nullable = false, unique = true)
    private String name_en;
}