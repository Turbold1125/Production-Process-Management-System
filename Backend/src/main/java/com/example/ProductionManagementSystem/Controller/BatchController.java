package com.example.ProductionManagementSystem.Controller;

import com.example.ProductionManagementSystem.Api.BatchApi;
import com.example.ProductionManagementSystem.DTOs.BatchRequest;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Batch;
import com.example.ProductionManagementSystem.Service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
public class BatchController implements BatchApi {

    private final BatchService batchService;

    @GetMapping("/all")
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }

    @PostMapping("/create")
    public Batch createBatch(@RequestBody BatchRequest request) throws ServiceException {
        return batchService.createBatch(request);
    }

    @PostMapping("/byLotId")
    public List<Batch> getBatchesByLotId(Integer lotId) {
        return batchService.getBatchesByLotId(lotId);
    }
}
