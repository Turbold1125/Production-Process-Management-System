// src/main/java/com/example/Change/Model/Const/FactoryProcess.java
package com.example.ProductionManagementSystem.Model.Const;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "factory_process")
@NoArgsConstructor
public class FactoryProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "name_en", nullable = false, unique = true)
    private String name_en;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "inputs")
    private String inputs;

    @Column(name = "inputs_en")
    private String inputs_en;

    @Column(name = "outputs", nullable = false)
    private String outputs;

    @Column(name = "outputs_en", nullable = false)
    private String outputs_en;

    @Column(name = "waste")
    private String waste;
}