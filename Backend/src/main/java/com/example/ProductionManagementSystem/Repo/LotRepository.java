package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LotRepository extends JpaRepository<Lot, Integer> {
    @Query("SELECT MAX(SUBSTRING(l.lotName, LENGTH(l.lotName) - 1, 2)) FROM Lot l WHERE l.orderId = :orderId")
    Optional<Integer> findMaxLotNumberByOrderId(@Param("orderId") Integer orderId);

    List<Lot> findByOrderId(Integer orderId);
}
