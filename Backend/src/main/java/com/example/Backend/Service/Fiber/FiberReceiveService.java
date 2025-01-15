//package com.example.Backend.Service.Fiber;
//
//import com.example.Backend.Constants.ProcessStatus;
//import com.example.Backend.DTOs.Process.FiberReceiveRequest;
//import com.example.Backend.DTOs.Process.FiberReceiveResponse;
//import com.example.Backend.Exception.ErrorResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Model.Const.FiberColor;
//import com.example.Backend.Model.Order;
//import com.example.Backend.Model.Fibers.FiberReceive;
//import com.example.Backend.Model.Process.ProcessDye;
//import com.example.Backend.Model.Process.ProcessReceive;
//import com.example.Backend.Repo.Const.FiberColorRepository;
//import com.example.Backend.Repo.Fibers.FiberReceiveRepository;
//import com.example.Backend.Repo.OrderRepository;
//import com.example.Backend.Repo.Process.DyeRepository;
//import com.example.Backend.Service.Process.ReceiveProcessService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FiberReceiveService {
//
//    private final FiberReceiveRepository fiberReceiveRepository;
//    private final OrderRepository orderRepository;
//    private final FiberColorRepository fiberColorRepository;
//    private final DyeRepository dyeRepository;
//    private final ReceiveProcessService receiveProcessService;
//
//    public List<FiberReceiveResponse> getAllFiberReceivesByOrderId(Long orderId) throws ServiceException {
//        List<FiberReceive> fiberReceives = fiberReceiveRepository.findByOrderId(orderId);
//
//        if (fiberReceives == null || fiberReceives.isEmpty()) {
//            throw new ServiceException(ErrorResponse.NO_CONTENT);
//        }
//
//        return fiberReceives.stream()
//                .map(FiberReceiveResponse::new)
//                .toList();
//    }
//
//    public List<FiberReceiveResponse> getAllFiberReceives() throws ServiceException {
//        List<FiberReceive> fiber = fiberReceiveRepository.findAll();
//
//        if (fiber.isEmpty()) {
//            throw new ServiceException(ErrorResponse.NO_CONTENT);
//        }
//
//        List<FiberReceiveResponse> response = new ArrayList<>();
//        for (FiberReceive fiberReceive : fiber) {
//            response.add(new FiberReceiveResponse(fiberReceive));
//        }
//        return response;
//    }
//
//    public FiberReceiveResponse getFiberReceiveById(Long id) throws ServiceException {
//        FiberReceive fiberReceive = fiberReceiveRepository.findById(id).orElse(null);
//        if (fiberReceive == null) {
//            throw new ServiceException(ErrorResponse.NO_FIBER_RECEIVE);
//        }
//        return new FiberReceiveResponse(fiberReceive);
//    }
//
//    public FiberReceiveResponse createFiberReceive(FiberReceiveRequest dto) throws ServiceException {
//
//        Order order = orderRepository.findById(dto.getOrderId())
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_ORDER));
//
//        ProcessReceive processReceive = receiveProcessService.getProcessReceiveById(dto.getProcessId());
//
//        ProcessDye processDye = dyeRepository.findById(dto.getProcessId())
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_PROCESS_RECEIVE));
//
//        FiberColor fiberColor = fiberColorRepository.findById(dto.getFiberColorId())
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_FIBER_COLOR));
//
//        double totalReceivedWeight = fiberReceiveRepository.calculateTotalReceivedWeightByOrderId(dto.getOrderId());
//
//        double orderWeight = order.getWeight();
//
//        if (totalReceivedWeight + dto.getRoughWeight() > orderWeight) {
//            throw new ServiceException(ErrorResponse.EXCEEDS_WEIGHT);
//        }
//
//        if (dto.getRoughWeight() > processReceive.getAvailable_weight()) {
//            throw new ServiceException(ErrorResponse.EXCEEDS_AVAILABLE_WEIGHT);
//        }
//
//        int lastBaleNum = fiberReceiveRepository.findLastBaleNumByOrderId(dto.getOrderId());
//
//        FiberReceive fiberReceive = new FiberReceive();
//        fiberReceive.setOrder(order);
//        fiberReceive.setProcessReceive(processReceive);
//        fiberReceive.setDateTime(LocalDateTime.now());
//        fiberReceive.setColor(fiberColor);
//        fiberReceive.setRough_weight(dto.getRoughWeight());
//        fiberReceive.setBale_weight(dto.getBaleWeight());
//        fiberReceive.setCon_weight(dto.getConWeight());
//        fiberReceive.setMoisture(dto.getMoisture());
//        fiberReceive.setBaleNum(lastBaleNum + 1);
//
//        receiveProcessService.setProcessReceiveInProgress(processReceive);
//
//        receiveProcessService.updateProcessReceiveWeights(processReceive, dto.getRoughWeight(), processDye);
//
////        receiveProcessService.updateProcessDyeWeights(processDye, dto.getRoughWeight());
//
//        FiberReceive savedFiberReceive = fiberReceiveRepository.save(fiberReceive);
//
//        return new FiberReceiveResponse(savedFiberReceive);
//    }
//
//    public void deleteFiberReceive(Long id) throws ServiceException {
//        if (!fiberReceiveRepository.existsById(id)) {
//            throw new ServiceException(ErrorResponse.NO_FIBER_RECEIVE);
//        }
//        fiberReceiveRepository.deleteById(id);
//    }
//
//    public double getTotalWeightProcessed(Long orderId) {
//        return fiberReceiveRepository.findAll()
//                .stream()
//                .filter(fiberReceive -> fiberReceive.getOrder().getId().equals(orderId))
//                .mapToDouble(FiberReceive::getRough_weight)
//                .sum();
//    }
//}