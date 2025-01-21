package com.example.ProductionManagementSystem.Service;

import com.example.ProductionManagementSystem.DTOs.LotRequest;
import com.example.ProductionManagementSystem.Model.Lot;
import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Repo.BatchRepository;
import com.example.ProductionManagementSystem.Repo.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LotService {

    private final LotRepository lotRepository;
    private final BatchRepository batchRepository;

    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public Lot createLot(LotRequest request) {

        int nextLotNumber = lotRepository.findMaxLotNumberByOrderId(request.getOrderId()).orElse(0) + 1;

        String lotName = String.format("LOT-%s-%02d", request.getOrderId(), nextLotNumber);

        Lot lot = new Lot();
        lot.setOrderId(request.getOrderId());
        lot.setLotName(lotName);
        lot.setMaterialName(request.getMaterialName());
        lot.setLotWeight(request.getLotWeight());
        lot.setCreatedDate(LocalDateTime.now());
        return lotRepository.save(lot);
    }

    public Lot createOnlyBlending(Process process) {
        int nextLotNumber = lotRepository.findMaxLotNumberByOrderId(process.getOrderId()).orElse(0) + 1;

        String lotName = String.format("LOT-%s-%02d", process.getOrderId(), nextLotNumber);

        Lot lot = new Lot();
        lot.setOrderId(process.getOrderId());
        lot.setLotName(lotName);
        lot.setMaterialName(process.getInputMaterial());
        lot.setLotWeight(process.getInputMaterialWeight());
        lot.setCreatedDate(LocalDateTime.now());
        return lotRepository.save(lot);
    }

    public List<Lot> getLotsByOrderId(Integer orderId){
        return lotRepository.findByOrderId(orderId);
    }
}
