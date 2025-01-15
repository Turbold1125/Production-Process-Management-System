package com.example.Backend.Repo;


import com.example.Backend.Model.Inventory;
import com.example.Backend.Model.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {

    Inventory findByCustomerAndMaterialAndColorAndFiberType(String customer, String material, String color, String fiberType);
}
