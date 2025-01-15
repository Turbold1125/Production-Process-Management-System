package com.example.Backend.Model.Const;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity(name = "FiberType")
@AllArgsConstructor
@NoArgsConstructor
public class FiberType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String name_en;
}
