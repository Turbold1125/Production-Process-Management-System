package com.example.Backend.Model;

import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Model.Const.FiberType;
import com.example.Backend.Model.Const.Material;
import com.example.Backend.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ServiceLoader;

@Data
@Entity(name = "Inventory")
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String material;

    private String customer;

    private String color;
    private String  fiberType;

    private double roughWeight;
    private double baleWeight;
    private double conWeight;

    private LocalDateTime dateTime;
}