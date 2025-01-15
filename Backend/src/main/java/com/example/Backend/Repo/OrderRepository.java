package com.example.Backend.Repo;

import com.example.Backend.DTOs.OrderResponse;
import com.example.Backend.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    @Query("SELECT o FROM OrderList o ORDER BY o.orderDate DESC LIMIT 5")
//    List<OrderResponse> findRecentOrders();
}
