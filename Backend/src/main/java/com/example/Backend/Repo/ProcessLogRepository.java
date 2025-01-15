package com.example.Backend.Repo;

import com.example.Backend.Model.ProcessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessLogRepository extends JpaRepository<ProcessLog, Long> {
    List<ProcessLog> findByOrderId(Long orderId);
}
