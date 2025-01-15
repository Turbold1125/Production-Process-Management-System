package com.example.Backend.Controller;

import com.example.Backend.Api.OrderApi;
import com.example.Backend.DTOs.OrderRequest;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Order;
import com.example.Backend.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() throws ServiceException {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) throws ServiceException {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequest orderDTO) throws ServiceException {
        return orderService.createOrder(orderDTO);
    }

    // Delete an order by ID
    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) throws ServiceException {
        orderService.deleteOrder(orderId);
    }
}
