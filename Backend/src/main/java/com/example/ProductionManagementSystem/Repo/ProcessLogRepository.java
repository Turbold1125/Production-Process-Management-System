package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Model.ProcessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessLogRepository extends JpaRepository<ProcessLog, Integer> {
    List<ProcessLog> findByOrderId(Integer orderId);

    Optional<ProcessLog> findByOrderIdAndProcessName(Integer orderId, String processName);

    List<ProcessLog> findAllByOrderByUpdatedTimeDesc();
}
