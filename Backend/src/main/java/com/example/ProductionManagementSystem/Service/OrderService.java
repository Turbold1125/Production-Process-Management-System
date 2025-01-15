package com.example.ProductionManagementSystem.Service;

import com.example.ProductionManagementSystem.Constants.ProcessStatus;
import com.example.ProductionManagementSystem.DTOs.OrderResponse;
import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.FactoryProcess;
import com.example.ProductionManagementSystem.Model.Const.Status;
import com.example.ProductionManagementSystem.Model.Order;
import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Repo.*;
import com.example.ProductionManagementSystem.Repo.Const.CustomerRepository;
import com.example.ProductionManagementSystem.Repo.Const.FactoryProcessRepository;
import com.example.ProductionManagementSystem.Repo.Const.FiberColorRepository;
import com.example.ProductionManagementSystem.Repo.Const.FiberTypeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final FactoryProcessRepository factoryProcessRepository;

    private final FiberColorRepository fiberColorRepository;
    private final CustomerRepository customerRepository;
    private final FiberTypeRepository fiberTypeRepository;
    private final ProcessRepository processRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<OrderResponse> getRecentOrders() {
        List<Order> recentOrders = orderRepository.findRecentOrders();
        return recentOrders.stream()
                .limit(5)
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getAllOrders() throws ServiceException {
        return orderRepository.findAll().stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Integer orderId) throws ServiceException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_ORDER));
        return new OrderResponse(order);
    }

    public OrderResponse createOrder(Order dto) throws ServiceException {

        customerRepository.findByName(dto.getCustomerName())
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CUSTOMER));

        fiberColorRepository.findByName(dto.getFiberColor())
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CUSTOMER));

        fiberTypeRepository.findByName(dto.getFiberType())
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_FIBER_TYPE));

        if (dto.getWeight() <= 0) {
            throw new ServiceException(ErrorResponse.INVALID_WEIGHT);
        }

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerName(dto.getCustomerName());
        order.setFiberColor(dto.getFiberColor());
        order.setFiberType(dto.getFiberType());
        order.setWeight(dto.getWeight());
        order.setStatus(Status.NEW);

        if (dto.getFactoryProcesses() == null || dto.getFactoryProcesses().isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_PROCESS);
        }

        List<FactoryProcess> processes = new ArrayList<>();
        for (FactoryProcess process : dto.getFactoryProcesses()) {

            logger.info("Processing FactoryProcess ID: {}", process.getId());

            FactoryProcess existingProcess = factoryProcessRepository.findById(process.getId())
                    .orElseThrow(() -> new ServiceException(ErrorResponse.NO_PROCESS));
            processes.add(existingProcess);
        }

        processes.sort(Comparator.comparingInt(FactoryProcess::getId));

        order.setFactoryProcesses(processes);

        Order savedOrder = orderRepository.save(order);

        for (FactoryProcess factoryProcess : processes) {
            Process process = new Process();
            process.setOrderId(savedOrder.getId());
            process.setProcessName(factoryProcess.getName());
            process.setCustomerName(savedOrder.getCustomerName());
            process.setStatus(ProcessStatus.NEW);
            processRepository.save(process);
        }

        return new OrderResponse(savedOrder);
    }

    public void deleteOrder(Integer orderId) throws ServiceException {
        if (!orderRepository.existsById(orderId)) {
            throw new ServiceException(ErrorResponse.NO_ORDER);
        }
        orderRepository.deleteById(orderId);
    }


    public void updateOrderStatus(Integer orderId, Status status) {
//        List<Process> processes = processRepository.findByOrderId(orderId);
//
//        boolean allCompleted = processes.stream().allMatch(p -> p.getStatus() == ProcessStatus.COMPLETED);
//        boolean anyInProgress = processes.stream().anyMatch(p -> p.getStatus() == ProcessStatus.IN_PROGRESS);
//
//        orderRepository.findById(orderId).ifPresent(order -> {
//            if (allCompleted) {
//                order.setStatus(Status.COMPLETED);
//            } else if (anyInProgress) {
//                order.setStatus(Status.IN_PROGRESS);
//            } else {
//                order.setStatus(Status.NEW);
//            }
//            orderRepository.save(order);
//        });

        orderRepository.findById(orderId).ifPresent(order -> {
            if (!order.getStatus().equals(status)) {
                order.setStatus(status);
                orderRepository.save(order);
            }
        });
    }
}