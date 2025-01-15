package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findByCustomerName(String customerName);
}
