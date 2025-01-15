package com.example.Backend.Service;

import com.example.Backend.Constants.ProcessStatus;
import com.example.Backend.DTOs.OrderRequest;
import com.example.Backend.Exception.ErrorResponse;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.FactoryProcess;
import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Model.Const.FiberType;
import com.example.Backend.Model.Process.*;
import com.example.Backend.Model.User.Customer;
import com.example.Backend.Model.Order;
import com.example.Backend.Repo.*;
import com.example.Backend.Repo.Const.CustomerRepository;
import com.example.Backend.Repo.Const.FactoryProcessRepository;
import com.example.Backend.Repo.Const.FiberColorRepository;
import com.example.Backend.Repo.Const.FiberTypeRepository;
import com.example.Backend.Repo.Process.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final FiberColorRepository colorRepository;
    private final FiberTypeRepository fiberTypeRepository;
    private final FactoryProcessRepository factoryProcessRepository;
    private final DyeRepository dyeRepository;
    private final BlendRepository blendRepository;
    private final CardRepository cardRepository;
    private final SpinRepository spinRepository;
    private final ShipRepository shipRepository;
    private final DoubleRepository doubleRepository;
    private final WindRepository windRepository;
    private final TwistRepository twistRepository;

    public List<Order> getAllOrders() throws ServiceException {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) throws ServiceException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_ORDER));
        return order;
    }

    public Order createOrder(OrderRequest dto) throws ServiceException {

        Customer customer = customerRepository.findById(dto.getCustomer())
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CUSTOMER));
        FiberColor color = colorRepository.findById(dto.getColor())
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_FIBER_COLOR));
        FiberType type = fiberTypeRepository.findById(dto.getType())
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_FIBER_TYPE));

        Order order = new Order();
        order.setCustomer(customer);
        order.setColor(color);
        order.setFiberType(type);
        order.setWeight(dto.getWeight());
        order.setOrderDate(LocalDateTime.now());

        if (dto.getProcesses() == null || dto.getProcesses().isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_PROCESS);
        }

        List<FactoryProcess> processes = new ArrayList<>();
        for (Long processId : dto.getProcesses()) {
            FactoryProcess process = factoryProcessRepository.findById(processId)
                    .orElseThrow(() -> new ServiceException(ErrorResponse.NO_PROCESS));
            processes.add(process);
        }

        order.setFactoryProcesses(processes);
        orderRepository.save(order);

        createProcessRecordsForOrder(order, dto.getProcesses());

        return order;
    }

    public void deleteOrder(Long orderId) throws ServiceException {
        if (!orderRepository.existsById(orderId)) {
            throw new ServiceException(ErrorResponse.NO_ORDER);
        }
        orderRepository.deleteById(orderId);
    }

    private void createProcessRecordsForOrder(Order order, List<Long> processIds) {
        for (Long processId : processIds) {
            switch (processId.intValue()) {
                case 1:
                        ProcessDye processDye = new ProcessDye();
                        processDye.setOrder(order);
                        processDye.setDateStart(null);
                        processDye.setDateEnd(null);
                        processDye.setStatus(ProcessStatus.NEW);
                        dyeRepository.save(processDye);
                    break;
                case 2:
                    ProcessBlend processBlend = new ProcessBlend();
                    processBlend.setOrder(order);
                    processBlend.setDateStart(null);
                    processBlend.setDateEnd(null);
                    processBlend.setTotal_weight(0);
                    processBlend.setAvailable_weight(0);
                    processBlend.setStatus(ProcessStatus.NEW);
                    blendRepository.save(processBlend);
                    break;
                case 3:
                    ProcessCard processCard = new ProcessCard();
                    processCard.setOrder(order);
                    processCard.setDateStart(null);
                    processCard.setDateEnd(null);
                    processCard.setTotal_weight(0);
                    processCard.setAvailable_weight(0);
                    processCard.setStatus(ProcessStatus.NEW);
                    cardRepository.save(processCard);
                    break;
                case 4:
                    ProcessSpin processSpin = new ProcessSpin();
                    processSpin.setOrder(order);
                    processSpin.setDateStart(null);
                    processSpin.setDateEnd(null);
                    processSpin.setTotal_weight(0);
                    processSpin.setAvailable_weight(0);
                    processSpin.setStatus(ProcessStatus.NEW);
                    spinRepository.save(processSpin);
                    break;
                case 5:
                    ProcessWind processWind = new ProcessWind();
                    processWind.setOrder(order);
                    processWind.setDateStart(null);
                    processWind.setDateEnd(null);
                    processWind.setTotal_weight(0);
                    processWind.setAvailable_weight(0);
                    processWind.setStatus(ProcessStatus.NEW);
                    windRepository.save(processWind);
                    break;
                case 6:
                    ProcessDouble processDouble = new ProcessDouble();
                    processDouble.setOrder(order);
                    processDouble.setDateStart(null);
                    processDouble.setDateEnd(null);
                    processDouble.setTotal_weight(0);
                    processDouble.setAvailable_weight(0);
                    processDouble.setStatus(ProcessStatus.NEW);
                    doubleRepository.save(processDouble);
                    break;
                case 7:
                    ProcessTwist processTwist = new ProcessTwist();
                    processTwist.setOrder(order);
                    processTwist.setDateStart(null);
                    processTwist.setDateEnd(null);
                    processTwist.setTotal_weight(0);
                    processTwist.setAvailable_weight(0);
                    processTwist.setStatus(ProcessStatus.NEW);
                    twistRepository.save(processTwist);
                    break;
                case 8:
                    ProcessShip processShip = new ProcessShip();
                    processShip.setOrder(order);
                    processShip.setDateTime(null);
                    processShip.setTotal_weight(0);
                    processShip.setAvailable_weight(0);
                    processShip.setStatus(ProcessStatus.NEW);
                    shipRepository.save(processShip);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown process ID: " + processId);
            }
        }
    }
}