package com.example.Backend.Repo;

import com.example.Backend.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByCustomerContainingIgnoreCaseAndMaterialContainingIgnoreCase(String customer, String material);

    Inventory findByCustomerAndMaterialAndColorAndFiberType(String customer, String material, String color, String fiberType);

    List<Inventory> findByCustomerContainingIgnoreCaseAndColorContainingIgnoreCaseAndFiberTypeContainingIgnoreCase(
            String customer, String color, String fiberType);

//    List<Inventory> findByCustomerAndMaterialAndColorAndFiberType(String customer, String material, String color, String fiberType);
}
//    @Query("SELECT MAX(i.baleNum) FROM Inventory i")
//    Integer findMaxBaleNum();
