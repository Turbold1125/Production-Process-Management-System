package com.example.Backend.Controller;

import com.example.Backend.Model.ProcessLog;
import com.example.Backend.Service.ProcessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/process-logs")
@RequiredArgsConstructor
public class ProcessLogController {

    private final ProcessLogService processLogService;

    @GetMapping("/order/{orderId}")
    public List<ProcessLog> getProcessLogsByOrderId(@PathVariable Long orderId) {
        return processLogService.getProcessLogsByOrderId(orderId);
    }

    @PostMapping
    public ProcessLog createProcessLog(@RequestBody ProcessLog processLog) {
        return processLogService.createProcessLog(processLog);
    }
}
