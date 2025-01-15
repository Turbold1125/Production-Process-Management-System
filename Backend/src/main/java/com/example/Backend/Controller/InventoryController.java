package com.example.Backend.Controller;

import com.example.Backend.Api.InventoryApi;
import com.example.Backend.Model.Inventory;
import com.example.Backend.Model.InventoryLog;
import com.example.Backend.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController implements InventoryApi {

    private final InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public Inventory getItemById(@PathVariable Long itemId) {
        return inventoryService.getItemById(itemId);
    }

    @PostMapping("/create")
    public Inventory createItem(@RequestBody Inventory item) {
        return inventoryService.createItem(item);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        inventoryService.deleteItem(itemId);
    }

    @GetMapping("/search")
    public List<Inventory> searchItems(@RequestParam String customer, @RequestParam String type) {
        return inventoryService.searchItems(customer, type);
    }

    @GetMapping("/inventoryLogs")
    public List <InventoryLog> getInventoryLogs() {
        return inventoryService.getInventoryLogs();
    }

    @GetMapping("/filter")
    public List<Inventory> filterInventory(
            @RequestParam String customer,
//            @RequestParam String material,
            @RequestParam String color,
            @RequestParam String fiberType) {
        return inventoryService.filterInventory(customer, color, fiberType);
    }

//    @GetMapping("/find")
//    public List<Inventory> findByCustomerAndMaterialAndColorAndFiberTypes(
//            @RequestParam String customer,
//            @RequestParam String material,
//            @RequestParam String color,
//            @RequestParam String fiberType) {
//        return inventoryService.findByCustomerAndMaterialAndColorAndFiberTypes(customer, material, color, fiberType);
//    }
//    @PostMapping("/fiberDye")
//    public FiberDye createFiberDye(@RequestBody FiberDye fiberDye) {
//        return inventoryService.createFiberDye(fiberDye);
//    }
}