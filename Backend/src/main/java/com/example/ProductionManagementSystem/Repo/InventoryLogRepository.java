package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Integer> {
//    InventoryLog findByCustomerIdAndFiberMaterialAndFiberColorAndFiberType(
//            Integer customerId, String fiberMaterial, String fiberColor, String fiberType);
    @Query("SELECT il FROM InventoryLog il ORDER BY il.timestamp DESC")
    List<InventoryLog> findAllByOrderByTimestampDesc();
}
