package com.example.ProductionManagementSystem.Model.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Table(name = "customer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "fax", nullable = false)
    private String fax;
}
