// src/main/java/com/example/Backend/Model/Const/FiberColor.java
package com.example.Backend.Model.Const;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "FiberColor")
@AllArgsConstructor
@NoArgsConstructor
public class FiberColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String name_en;
}