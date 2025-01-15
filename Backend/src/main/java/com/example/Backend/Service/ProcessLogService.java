package com.example.Backend.Service;

import com.example.Backend.Model.ProcessLog;
import com.example.Backend.Repo.ProcessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessLogService {

    private final ProcessLogRepository processLogRepository;

    public List<ProcessLog> getProcessLogsByOrderId(Long orderId) {
        return processLogRepository.findByOrderId(orderId);
    }

    public ProcessLog createProcessLog(@RequestBody ProcessLog processLog) {
        return processLogRepository.save(processLog);
    }
}
