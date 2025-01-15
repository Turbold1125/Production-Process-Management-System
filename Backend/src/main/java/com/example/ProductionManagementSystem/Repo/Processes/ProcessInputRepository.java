package com.example.ProductionManagementSystem.Repo.Processes;

import com.example.ProductionManagementSystem.Model.Processes.ProcessInput;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessInputRepository  extends JpaRepository<ProcessInput, Integer> {
    List<ProcessInput> findByProcessId(Integer processId);

    List<ProcessInput> findByProcessIdIn(List<Integer> list);

    List<ProcessInput> findByOrderId(Integer orderId);

    List<ProcessInput> findByInventoryId(Integer inventoryId);

}
