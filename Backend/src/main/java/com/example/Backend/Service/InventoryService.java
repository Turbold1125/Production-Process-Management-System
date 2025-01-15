package com.example.Backend.Service;

import com.example.Backend.Model.Inventory;
import com.example.Backend.Model.InventoryLog;
import com.example.Backend.Repo.Fibers.FiberDyeRepository;
import com.example.Backend.Repo.InventoryLogRepository;
import com.example.Backend.Repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryLogRepository inventoryLogRepository;
    private final FiberDyeRepository fiberDyeRepository;

    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    public Inventory getItemById(Long itemId) {
        return inventoryRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Inventory createItem(Inventory item) {
        item.setDateTime(LocalDateTime.now());
        item.setConWeight(item.getRoughWeight() - item.getBaleWeight());

        Inventory existingItem = inventoryRepository.findByCustomerAndMaterialAndColorAndFiberType(
                item.getCustomer(), item.getMaterial(), item.getColor(), item.getFiberType());

        if (existingItem != null) {
            existingItem.setRoughWeight(existingItem.getRoughWeight() + item.getRoughWeight());
            existingItem.setBaleWeight(existingItem.getBaleWeight() + item.getBaleWeight());
            existingItem.setConWeight(existingItem.getConWeight() + item.getConWeight());
            existingItem.setDateTime(LocalDateTime.now());
            Inventory updatedInventory = inventoryRepository.save(existingItem);
            logInventoryAction(updatedInventory, "UPDATE");
            return updatedInventory;
        } else {
            Inventory savedInventory = inventoryRepository.save(item);
            logInventoryAction(savedInventory, "CREATE");
            return savedInventory;
        }
    }

    public void deleteItem(Long itemId) {
        inventoryRepository.deleteById(itemId);
    }

    public List<Inventory> searchItems(String customer, String material) {
        return inventoryRepository.findByCustomerContainingIgnoreCaseAndMaterialContainingIgnoreCase(customer, material);
    }

    public List<Inventory> filterInventory(String customer, String color, String fiberType) {
        return inventoryRepository.findByCustomerContainingIgnoreCaseAndColorContainingIgnoreCaseAndFiberTypeContainingIgnoreCase(
                customer, color, fiberType);
    }

//    public FiberDye createFiberDye(FiberDye fiberDye) {
//        fiberDye.setDateTime(LocalDateTime.now());
//        FiberDye savedFiberDye = fiberDyeRepository.save(fiberDye);
//        logInventoryAction(fiberDye.getInventory(), "DYEING");
//        return savedFiberDye;
//    }

    private void logInventoryAction(Inventory inventory, String action) {
        InventoryLog log = new InventoryLog();
        log.setInventoryId(inventory.getId());
        log.setAction(action);
        log.setCustomer(inventory.getCustomer());
        log.setMaterial(inventory.getMaterial());
        log.setColor(inventory.getColor());
        log.setFiberType(inventory.getFiberType());
        log.setRoughWeight(inventory.getRoughWeight());
        log.setBaleWeight(inventory.getBaleWeight());
        log.setConWeight(inventory.getConWeight());
        log.setTimestamp(LocalDateTime.now());
        inventoryLogRepository.save(log);
    }

    public List<InventoryLog> getInventoryLogs() {
        return inventoryLogRepository.findAll();
    }
}