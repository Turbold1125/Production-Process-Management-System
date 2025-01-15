package com.example.ProductionManagementSystem.Service;

import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Model.ProcessLog;
import com.example.ProductionManagementSystem.Repo.ProcessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessLogService {

    private final ProcessLogRepository processLogRepository;

    public List<ProcessLog> getProcessLogsByOrderId(Integer orderId) {
        return processLogRepository.findByOrderId(orderId);
    }

    public ProcessLog createProcessLog(@RequestBody ProcessLog processLog) {
        return processLogRepository.save(processLog);
    }

    public void logProcessAction(Process process, String action) {
        ProcessLog log = processLogRepository.findByOrderIdAndProcessName(process.getOrderId(), process.getProcessName())
                .orElse(new ProcessLog());

        if (log.getId() == null) {
            log.setProcessName(process.getProcessName());
            log.setOrderId(process.getOrderId());
            log.setInputMaterial(process.getInputMaterial());
            log.setInputMaterialWeight(process.getInputMaterialWeight());
        } else {
            log.setOutputMaterial(process.getOutputMaterial());
            log.setOutputMaterialWeight(process.getOutputMaterialWeight());
        }

        if (action.equals("START")) {
            log.setProcessStartTime(LocalDateTime.now());
        } else if (action.equals("COMPLETE")) {
            log.setProcessEndTime(LocalDateTime.now());
        }

        log.setUpdatedTime(LocalDateTime.now());
        processLogRepository.save(log);
    }
}
