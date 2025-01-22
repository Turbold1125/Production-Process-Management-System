package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByfiberMaterial(String material);

    @Query("SELECT i FROM Inventory i " +
            "WHERE (:customerName IS NULL OR LOWER(i.customerName) LIKE LOWER(CONCAT('%', :customerName, '%'))) " +
            "AND (:fiberMaterial IS NULL OR i.fiberMaterial = :fiberMaterial) ")
    List<Inventory> searchInventory(
            @Param("customerName") String customerName,
            @Param("fiberMaterial") String fiberMaterial);

    List<Inventory> findByCustomerNameAndFiberMaterialIn(String customerName, List<String> fiberMaterials);

    List<Inventory> findByCustomerName(String customerName);

    @Query("SELECT MAX(i.baleNum) FROM Inventory i WHERE i.customerName = :customerName AND i.fiberMaterial = :fiberMaterial")
    Integer findMaxBaleNumByCustomerAndMaterial(@Param("customerName") String customerName, @Param("fiberMaterial") String fiberMaterial);

}