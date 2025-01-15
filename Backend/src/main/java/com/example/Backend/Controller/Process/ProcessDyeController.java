package com.example.Backend.Controller;

import com.example.Backend.DTOs.DyeingProcessRequest;
import com.example.Backend.Model.Process.DyeingProcess;
import com.example.Backend.Service.DyeingProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dyeing-process")
@RequiredArgsConstructor
public class ProcessDyeController {

    private final DyeingProcessService dyeingProcessService;

    @PostMapping("/start")
    public ResponseEntity<String> startDyeingProcess(@RequestBody DyeingProcessRequest request) {
        try {
            dyeingProcessService.startDyeingProcess(
                    request.getMaterial(),
                    request.getInputWeight(),
                    request.getFiberType(),
                    request.getColor(),
                    request.getCustomer(),
                    request.getOrderId()
            );
            return ResponseEntity.ok("Dyeing process started successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/complete/{dyeingProcessId}")
    public ResponseEntity<String> completeDyeingProcess(
            @PathVariable Long dyeingProcessId,
            @RequestParam Double outputWeight) {
        try {
            dyeingProcessService.completeDyeingProcess(dyeingProcessId, outputWeight);
            return ResponseEntity.ok("Dyeing process completed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}