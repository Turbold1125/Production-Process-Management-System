package com.example.ProductionManagementSystem.Service;

import com.example.ProductionManagementSystem.DTOs.DeliveryRequest;
import com.example.ProductionManagementSystem.DTOs.DeliveryResponse;
import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Delivery;
import com.example.ProductionManagementSystem.Model.DeliveryItem;
import com.example.ProductionManagementSystem.Model.Inventory;
import com.example.ProductionManagementSystem.Model.Processes.ProcessInput;
import com.example.ProductionManagementSystem.Model.Processes.ProcessOutput;
import com.example.ProductionManagementSystem.Repo.DeliveryRepository;
import com.example.ProductionManagementSystem.Repo.InventoryRepository;
import com.example.ProductionManagementSystem.Repo.ProcessRepository;
import com.example.ProductionManagementSystem.Repo.Processes.ProcessInputRepository;
import com.example.ProductionManagementSystem.Repo.Processes.ProcessOutputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final InventoryRepository inventoryRepository;
    private final ProcessRepository processRepository;
    private final ProcessInputRepository processInputRepository;
    private final ProcessOutputRepository processOutputRepository;
    private final InventoryService inventoryService;


    public List<Delivery> getDeliveriesByCustomer() {
        return deliveryRepository.findAll();
    }

    public DeliveryResponse deliverInventory(DeliveryRequest request) throws ServiceException {
        if (!"INVENTORY".equalsIgnoreCase(request.getDeliveryType())) {
            throw new ServiceException(ErrorResponse.NO_INVENTORY);
        }

        String customerName = request.getCustomerName();
        if (customerName == null || customerName.isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_CUSTOMER);
        }

        List<Inventory> inventoryList;

        if (Boolean.TRUE.equals(request.getDeliverAll())) {
            inventoryList = inventoryRepository.findByCustomerName(customerName);
        } else if (request.getInventoryIds() != null && !request.getInventoryIds().isEmpty()) {
            inventoryList = inventoryRepository.findAllById(request.getInventoryIds()).stream()
                    .filter(inv -> inv.getCustomerName().equals(customerName))
                    .collect(Collectors.toList());

            if (inventoryList.isEmpty()) {
                throw new ServiceException(ErrorResponse.NO_INVENTORY_TO_DELIVER);
            }
        } else {
            throw new ServiceException(ErrorResponse.NO_INVENTORY_SELECTED);
        }

        // Create a new Delivery record
        Delivery delivery = new Delivery();
        delivery.setDeliveryType("INVENTORY");
        delivery.setCustomerName(customerName);
        delivery.setAddress(request.getAddress() != null ? request.getAddress() : "Хаяг байхгүй");
        delivery.setDeliveryTime(LocalDateTime.now());

        List<DeliveryItem> deliveryItems = new ArrayList<>();
        for (Inventory inventory : inventoryList) {

            DeliveryItem item = new DeliveryItem();
            item.setDelivery(delivery);
            item.setInventoryId(inventory.getId());
            item.setMaterial(inventory.getFiberMaterial());
            item.setWeight(inventory.getConWeight());
            item.setColor(inventory.getFiberColor());
            item.setFiberType(inventory.getFiberType());
            item.setFiberColor(inventory.getFiberColor());
            item.setDeliveredTime(LocalDateTime.now());


            deliverInventory(inventory);
            deliveryItems.add(item);
        }

        delivery.setItems(deliveryItems);
        Delivery savedDelivery = deliveryRepository.save(delivery);

        DeliveryResponse response = new DeliveryResponse();
        response.setDeliveryId(savedDelivery.getId());
        response.setDeliveredInventoryIds(inventoryList.stream()
                .map(Inventory::getId).collect(Collectors.toList()));
        return response;
    }

    private boolean isProcessed(Inventory inventory) {
        List<ProcessInput> inputs = processInputRepository.findByInventoryId(inventory.getId());
        List<ProcessOutput> outputs = processOutputRepository.findByInventoryId(inventory.getId());
        return (inputs != null && !inputs.isEmpty()) || (outputs != null && !outputs.isEmpty());
    }


    private void deliverInventory(Inventory inventory) {
        inventoryService.logInventoryAction(inventory, "DELIVERED");
        inventoryRepository.delete((inventory));
    }
}
