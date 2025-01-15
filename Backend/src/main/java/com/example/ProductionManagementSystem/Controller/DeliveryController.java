package com.example.ProductionManagementSystem.Controller;

import com.example.ProductionManagementSystem.DTOs.DeliveryRequest;
import com.example.ProductionManagementSystem.DTOs.DeliveryResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Delivery;
import com.example.ProductionManagementSystem.Service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/deliver-inventory")
    public DeliveryResponse deliverInventoryItems(@RequestBody DeliveryRequest request) throws  ServiceException{
        return deliveryService.deliverInventory(request);
    }

    @GetMapping("/delivered")
    public List<Delivery> getDeliveredItems() {
        return deliveryService.getDeliveriesByCustomer();
    }
}
