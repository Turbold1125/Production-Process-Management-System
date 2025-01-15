package com.example.Backend.Service;

import com.example.Backend.Model.Inventory;
import com.example.Backend.Model.InventoryLog;
import com.example.Backend.Model.Order;
import com.example.Backend.Model.Process.DyeingProcess;
import com.example.Backend.Model.ProcessLog;
import com.example.Backend.Repo.OrderRepository;
import com.example.Backend.Repo.Process.DyeingProcessRepository;
import com.example.Backend.Repo.InventoryLogRepository;
import com.example.Backend.Repo.InventoryRepository;
import com.example.Backend.Repo.ProcessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DyeingProcessService {

    private final InventoryRepository inventoryRepository;
    private final ProcessLogRepository processLogRepository;
    private final InventoryLogRepository inventoryLogRepository;
    private final DyeingProcessRepository dyeingProcessRepository;
    private final OrderRepository orderRepository;

    public void startDyeingProcess(String material, Double inputWeight, String fiberType, String color, String customer, Long orderId) {

        Inventory fiber = inventoryRepository.findByCustomerAndMaterialAndColorAndFiberType(customer, material, color, fiberType);

        if (fiber == null) {
            throw new RuntimeException("Fiber not found");
        }

        if (fiber.getConWeight() < inputWeight) {
            throw new RuntimeException("Not enough fiber");
        }

        fiber.setConWeight(fiber.getConWeight() - inputWeight);
        inventoryRepository.save(fiber);

        logInventoryAction(fiber, "UPDATE");

        DyeingProcess dyeingProcess = new DyeingProcess();
        dyeingProcess.setOrderId(orderId);
        dyeingProcess.setMaterial(material);
        dyeingProcess.setFiberType(fiberType);
        dyeingProcess.setColor(color);
        dyeingProcess.setCustomer(customer);
        dyeingProcess.setInputWeight(inputWeight);
        dyeingProcess.setStatus("IN_PROGRESS");
        dyeingProcess.setStartTime(LocalDateTime.now());
        dyeingProcessRepository.save(dyeingProcess);

        ProcessLog processLog = new ProcessLog();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        processLog.setOrder(order);
        processLog.setProcessName("Dyeing");
        processLog.setInputQuantity(inputWeight);
        processLog.setProcessStartTime(LocalDateTime.now());
        processLogRepository.save(processLog);
    }

    public void completeDyeingProcess(Long dyeingProcessId, Double outputWeight) {

        DyeingProcess dyeingProcess = dyeingProcessRepository.findById(dyeingProcessId)
                .orElseThrow(() -> new RuntimeException("Dyeing process not found"));

        dyeingProcess.setOutputWeight(outputWeight);
        dyeingProcess.setStatus("COMPLETED");
        dyeingProcess.setEndTime(LocalDateTime.now());
        dyeingProcessRepository.save(dyeingProcess);

        Inventory dyedFiber = inventoryRepository.findByCustomerAndMaterialAndColorAndFiberType(
                dyeingProcess.getCustomer(), dyeingProcess.getMaterial() + " (Dyed)", dyeingProcess.getColor(), dyeingProcess.getFiberType());

        if (dyedFiber != null) {

            dyedFiber.setConWeight(dyedFiber.getConWeight() + outputWeight);
            dyedFiber.setDateTime(LocalDateTime.now());
            inventoryRepository.save(dyedFiber);

            logInventoryAction(dyedFiber, "UPDATE");
        } else {

            dyedFiber = new Inventory();
            dyedFiber.setCustomer(dyeingProcess.getCustomer());
            dyedFiber.setMaterial(dyeingProcess.getMaterial() + " (Dyed)");
            dyedFiber.setFiberType(dyeingProcess.getFiberType());
            dyedFiber.setColor(dyeingProcess.getColor());
            dyedFiber.setConWeight(outputWeight);
            dyedFiber.setDateTime(LocalDateTime.now());
            inventoryRepository.save(dyedFiber);

            logInventoryAction(dyedFiber, "DYEING");
        }
    }

    private void logInventoryAction(Inventory inventory, String action) {
        InventoryLog log = new InventoryLog();
        log.setInventoryId(inventory.getId());
        log.setAction(action);
        log.setCustomer(inventory.getCustomer());
        log.setMaterial(inventory.getMaterial());
        log.setColor(inventory.getColor());
        log.setFiberType(inventory.getFiberType());
        log.setConWeight(inventory.getConWeight());
        log.setTimestamp(LocalDateTime.now());
        inventoryLogRepository.save(log);
    }
}