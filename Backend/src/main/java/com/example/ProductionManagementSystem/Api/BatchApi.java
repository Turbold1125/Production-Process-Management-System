package com.example.ProductionManagementSystem.Api;

import com.example.ProductionManagementSystem.DTOs.BatchRequest;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Batch;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Batch", description = "Батчийн API")
public interface BatchApi {

    List<Batch> getAllBatches();

    Batch createBatch(@RequestBody BatchRequest request) throws ServiceException;

    List<Batch> getBatchesByLotId(Integer lotId);
}
