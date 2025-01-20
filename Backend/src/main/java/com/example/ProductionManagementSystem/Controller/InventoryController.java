package com.example.ProductionManagementSystem.Controller;

import com.example.ProductionManagementSystem.Api.InventoryApi;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Inventory;
import com.example.ProductionManagementSystem.Model.InventoryLog;
import com.example.ProductionManagementSystem.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController implements InventoryApi {

    private final InventoryService inventoryService;

    @PostMapping("/create")
    public List<Inventory> createInventory(@RequestBody List<Inventory> inventoryData) throws ServiceException{
        return inventoryService.createInventory(inventoryData);
    }

    @GetMapping("/all")
    public List<Inventory> getAllInventory() throws ServiceException {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/search")
    public List<Inventory> searchItems (
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String fiberMaterial) throws ServiceException{
        return inventoryService.searchItems(customerName, fiberMaterial);
    }

    @GetMapping("/filter")
    public List<Inventory> getFilteredInventory(
            @RequestParam String customerName,
            @RequestParam String processType) {
        return  inventoryService.getFilteredInventory(customerName, processType);
    }

    @GetMapping("/logs")
    public List<InventoryLog> getInventoryLogs() throws ServiceException {
        return inventoryService.getInventoryLogs();
    }

}
