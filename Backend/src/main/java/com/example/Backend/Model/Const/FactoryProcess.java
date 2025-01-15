package com.example.Backend.Model.Const;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Process")
@AllArgsConstructor
@NoArgsConstructor
public class FactoryProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String name_en;

    private String description;

    private String outputs;

    private String outputs_en;
}
