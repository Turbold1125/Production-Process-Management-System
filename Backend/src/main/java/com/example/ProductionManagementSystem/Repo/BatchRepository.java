package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BatchRepository  extends JpaRepository<Batch, Integer> {
    @Query("SELECT MAX(SUBSTRING(b.batchName, LENGTH(b.batchName) - 1, 2)) " +
            "FROM Batch b WHERE b.lotId = :lotId AND b.processName = :processName")
    Optional<Integer> findMaxBatchNumberByLotIdAndProcessName(
                                                                @Param("lotId") Integer lotId,
                                                                @Param("processName") String processName);

    List<Batch> findByLotId(Integer lotId);
}