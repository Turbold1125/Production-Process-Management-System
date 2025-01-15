package com.example.ProductionManagementSystem.Service;

import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.*;
import com.example.ProductionManagementSystem.Model.Const.MatEnum;
import com.example.ProductionManagementSystem.Repo.InventoryRepository;
import com.example.ProductionManagementSystem.Repo.InventoryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryLogRepository inventoryLogRepository;

    public List<Inventory> getAllInventory() throws ServiceException {
        return inventoryRepository.findAll();
    }

    public Inventory validateAndDeductInventory(Integer inventoryId, double weight, String customerName, String processName) {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElse(null);
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory not found");
        }

        if (!inventory.getCustomerName().equals(customerName)) {
            throw new IllegalArgumentException("Customer name does not match");
        }

        if (inventory.getConWeight() < weight) {
            throw new IllegalArgumentException("Not enough weight in inventory");
        }

        inventory.setConWeight(inventory.getConWeight() - weight);

        if (inventory.getConWeight() <= 0) {
            inventoryRepository.delete(inventory);
        } else {
            inventoryRepository.save(inventory);
        }

        logInventoryAction(inventory, processName);

        return inventory;
    }

    public Inventory createProcessedInventory(String material, String color, double weight, String customerName, String fiberType) {
        Inventory inventory = new Inventory();
        inventory.setFiberMaterial(material);
        inventory.setFiberColor(color != null ? color : "Default Color");
        inventory.setFiberType(fiberType);
        inventory.setCustomerName(customerName);
        inventory.setRoughWeight(weight);
        inventory.setConWeight(weight);
        inventory.setDateTime(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    public Inventory createWasteInventory(String material, double weight, String customerName, String color) {
        Inventory inventory = new Inventory();
        inventory.setFiberMaterial(material);
        inventory.setFiberColor(color != null ? color : "Default Color");
        inventory.setFiberType("Waste");
        inventory.setCustomerName(customerName);
        inventory.setRoughWeight(weight);
        inventory.setConWeight(weight);
        inventory.setDateTime(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

//    InventoryLog log = new InventoryLog();
//        log.setInventoryId(inventory.getId());
//        log.setAction("DEDUCT");
//        log.setCustomerName(customerName);
//        log.setFiberMaterial(inventory.getFiberMaterial());
//        log.setFiberColor(inventory.getFiberColor());
//        log.setFiberType(inventory.getFiberType());
//        log.setRoughWeight(inventory.getRoughWeight());
//        log.setBaleWeight(inventory.getBaleWeight());
//        log.setBobbinWeight(inventory.getBobbinWeight());
//        log.setBobbinNum(inventory.getBobbinNum());
//        log.setBaleNum(inventory.getBaleNum());
//        log.setConWeight(inventory.getConWeight());
//        log.setTimestamp(LocalDateTime.now());
//        inventoryLogRepository.save(log);
//
//        return inventory;
//}

public Inventory getInventoryById(Integer id) {
    return inventoryRepository.findById(id).orElse(null);
}

public List<Inventory> searchItems(String customerName, String fiberMaterial) {
    if ((customerName == null || customerName.isEmpty()) && fiberMaterial.isEmpty()) {
        return inventoryRepository.findAll();
    } else if (customerName != null && !customerName.isEmpty() && fiberMaterial.isEmpty()) {
        return inventoryRepository.findByCustomerNameContaining(customerName);
    } else if (customerName == null || customerName.isEmpty()) {
        return inventoryRepository.findByFiberMaterialContaining(fiberMaterial);
    } else {
        return inventoryRepository.findByCustomerNameContainingAndFiberMaterialContaining(customerName, fiberMaterial);
    }
}

public Inventory createInventory(Inventory item) {

    MatEnum matEnum = MatEnum.fromName(item.getFiberMaterial());

    switch (matEnum) {
        case FIBER:
            item.setConWeight(item.getRoughWeight() - item.getBaleWeight());
            item.setBaleNum(1);
            item.setBaleWeight(item.getBaleWeight());
            item.setBobbinNum(null);
            item.setBobbinWeight(null);
            break;
        case FIBERBLENDED:
            item.setConWeight(item.getRoughWeight() - item.getBaleWeight());
            item.setBobbinNum(item.getBobbinNum());
            item.setBaleWeight(item.getBaleWeight());
            item.setBobbinWeight(null);
            item.setBaleNum(null);
            item.setBobbinNum(null);
            break;
        case ROVEN:
        case SINGLE_YARN:
        case WINDED_YARN:
        case DOUBLED_YARN:
        case TWISTED_YARN:
            item.setConWeight(item.getRoughWeight() - item.getBobbinWeight());
            item.setBobbinNum(item.getBobbinNum());
            item.setBobbinWeight(item.getBobbinWeight());
            item.setBaleNum(null);
            item.setBaleWeight(null);
            break;
        default:
            throw new IllegalArgumentException("Unsupported fiber material: " + item.getFiberMaterial());
    }
    item.setDateTime(LocalDateTime.now());

    Inventory savedItem = inventoryRepository.save(item);
    logInventoryAction(savedItem, "CREATE");
    return savedItem;
}

public List<Inventory> getFilteredInventory(String customerName, String processType) {
    switch (processType) {
        case "Будах":
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, Arrays.asList("Түүхий эд"));
        case "Холих":
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, Arrays.asList("Түүхий эд", "Цувимал"));
        case "Цувих":
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, Arrays.asList("Хольсон түүхий эд", "Цувимал"));
        case "Ээрэх":
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, Arrays.asList("Цувимал"));
        case "Ороох":
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, Arrays.asList("Дан утас"));
        case "Давхарлах":
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, Arrays.asList("Ороосон утас"));
        case "Мушгих":
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, Arrays.asList("Давхарласан утас"));
        default:
            return new ArrayList<>();
    }
}

public void logInventoryAction(Inventory inventory, String action) {
    InventoryLog log = new InventoryLog();
    log.setInventoryId(inventory.getId());
    log.setAction(action);
    log.setCustomerName(inventory.getCustomerName());
    log.setFiberMaterial(inventory.getFiberMaterial());
    log.setFiberColor(inventory.getFiberColor());
    log.setFiberType(inventory.getFiberType());
    log.setRoughWeight(inventory.getRoughWeight());
    log.setBaleWeight(inventory.getBaleWeight());
    log.setBobbinWeight(inventory.getBobbinWeight());
    log.setBobbinNum(inventory.getBobbinNum());
    log.setBaleNum(inventory.getBaleNum());
    log.setConWeight(inventory.getConWeight());
    log.setTimestamp(LocalDateTime.now());
    inventoryLogRepository.save(log);
}

public List<InventoryLog> getInventoryLogs() throws ServiceException {
    return inventoryLogRepository.findAll();
}
}