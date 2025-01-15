package com.example.ProductionManagementSystem.Repo.Processes;

import com.example.ProductionManagementSystem.Model.Const.OutputType;
import com.example.ProductionManagementSystem.Model.Processes.ProcessOutput;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessOutputRepository extends JpaRepository<ProcessOutput, Integer> {
    List<ProcessOutput> findByProcessId(Integer processId);

    List<ProcessOutput> findByProcessIdAndType(Integer processId, OutputType type);

    List<ProcessOutput> findByProcessIdIn(List<Integer> list);

    List<ProcessOutput> findByOrderId(Integer orderId);

    List<ProcessOutput> findByInventoryId(Integer inventoryId);
}
