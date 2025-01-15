package com.example.ProductionManagementSystem.Repo;

import com.example.ProductionManagementSystem.Constants.ProcessStatus;
import com.example.ProductionManagementSystem.Model.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcessRepository extends JpaRepository<Process, Integer> {

    Process findByOrderIdAndProcessName(Integer orderId, String processName);

    Optional<Process> findById(Integer ProcessId);
    List<Process> findByOrderId(Integer orderId);
    List<Process> findByCustomerName(String customerName);
    List<Process> findByStatus(ProcessStatus status);
    List<Process> findByStatusNot(String status);
    List<Process> findByUserId(Integer userId);
}
