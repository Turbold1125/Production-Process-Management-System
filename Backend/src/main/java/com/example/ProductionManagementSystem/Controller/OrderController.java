package com.example.ProductionManagementSystem.Controller;

import com.example.ProductionManagementSystem.Api.OrderApi;
import com.example.ProductionManagementSystem.DTOs.Order.OrderResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Order;
import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Service.OrderService;
import com.example.ProductionManagementSystem.Service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    private  final ProcessService processService;

    @GetMapping("/{id}/processes")
    public List<Process> getProcessesByOrderId(@PathVariable Integer id) {
        return processService.getProcessesByOrderId(id);
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders() throws ServiceException {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Integer id) throws ServiceException {
        return orderService.getOrderById(id);
    }

    @PostMapping("/create")
    public OrderResponse createOrder(@RequestBody Order order) throws ServiceException {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(Integer orderId) throws ServiceException {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/recent")
    public List<OrderResponse> getRecentOrders() {
        return orderService.getRecentOrders();
    }
}
