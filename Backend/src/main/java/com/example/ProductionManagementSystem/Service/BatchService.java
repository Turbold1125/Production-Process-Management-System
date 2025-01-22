package com.example.ProductionManagementSystem.Service;

import com.example.ProductionManagementSystem.DTOs.BatchRequest;
import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Batch;
import com.example.ProductionManagementSystem.Model.Lot;
import com.example.ProductionManagementSystem.Repo.BatchRepository;
import com.example.ProductionManagementSystem.Repo.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final LotRepository lotRepository;
    private final BatchRepository batchRepository;

    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    public Batch createBatch(BatchRequest request) throws ServiceException{
        Lot lot = lotRepository.findById(request.getLotId()).orElseThrow(() -> new RuntimeException("Lot not found"));

        if (lot.getRemainingWeight() < request.getBatchSize()) {
            throw new ServiceException(ErrorResponse.BATCH_SIZE_EXCEEDS_REMAINING_WEIGHT.withDynamicMessage(lot.getRemainingWeight()));
        }

        String batchName = String.format(
                "BATCH-%02d-%s",
                generateNextBatchNumber(request.getLotId(), request.getProcessName()),
                request.getProcessName()
        );

        Batch batch = new Batch();
        batch.setLotId(request.getLotId());
        batch.setLotName(lot.getLotName());
        batch.setBatchName(batchName);
        batch.setProcessName(request.getProcessName());
        batch.setWeight(request.getBatchSize());
        batch.setCreatedDate(LocalDateTime.now());

        Batch savedBatch = batchRepository.save(batch);

        lot.setRemainingWeight(lot.getRemainingWeight() - request.getBatchSize());
        lotRepository.save(lot);

        return savedBatch;
    }

    public List<Batch> getBatchesByLotId(Integer lotId){
        return batchRepository.findByLotId(lotId);
    }

    private int generateNextBatchNumber(Integer lotId, String processName) {
        return batchRepository.findMaxBatchNumberByLotIdAndProcessName(lotId, processName).orElse(0) + 1;
    }
}
