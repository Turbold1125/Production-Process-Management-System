package com.example.Backend.Service;

import com.example.Backend.Model.InventoryLog;
import com.example.Backend.Repo.InventoryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryLogService {
    private final InventoryLogRepository inventoryLogRepository;

    public List<InventoryLog> getAllInventoryLogs() {
        return inventoryLogRepository.findAll();
    }
}