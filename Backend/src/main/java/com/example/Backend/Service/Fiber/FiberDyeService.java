//package com.example.Backend.Service.Fiber;
//
//import com.example.Backend.Constants.ProcessStatus;
//import com.example.Backend.DTOs.Process.FiberDyeRequest;
//import com.example.Backend.DTOs.Process.FiberDyeResponse;
//import com.example.Backend.Exception.ErrorResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Model.Const.FiberColor;
//import com.example.Backend.Model.Fibers.FiberDye;
//import com.example.Backend.Model.Order;
//import com.example.Backend.Model.Process.ProcessDye;
//import com.example.Backend.Repo.Const.FiberColorRepository;
//import com.example.Backend.Repo.OrderRepository;
//import com.example.Backend.Repo.Fibers.FiberDyeRepository;
//import com.example.Backend.Repo.Fibers.FiberReceiveRepository;
//import com.example.Backend.Repo.Process.DyeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FiberDyeService {
//
//    private final FiberDyeRepository fiberDyeRepository;
//    private final FiberReceiveRepository fiberReceiveRepository;
//    private final OrderRepository orderRepository;
//    private final FiberColorRepository fiberColorRepository;
//    private final DyeRepository dyeRepository;
//
//    private final FiberReceiveService fiberReceiveService;
//
//    public List<FiberDyeResponse> getAllFiberDyes() throws ServiceException {
//        List<FiberDye> fiberDyes = fiberDyeRepository.findAll();
//        return fiberDyes.stream().map(FiberDyeResponse::new).toList();
//    }
//
//    public FiberDyeResponse getFiberDyeById(Long id) throws ServiceException {
//        FiberDye fiberDye = fiberDyeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("FiberDye not found with ID: " + id));
//        return new FiberDyeResponse(fiberDye);
//    }
//
//    public List<FiberDyeResponse> getAllFiberDyesByOrderId(Long orderId) throws ServiceException {
//        List<FiberDye> fiberDyes = fiberDyeRepository.findByOrderId(orderId);
//
//        if (fiberDyes == null || fiberDyes.isEmpty()) {
//            throw new ServiceException(ErrorResponse.NO_CONTENT);
//        }
//
//        List<FiberDyeResponse> responseDTOs = fiberDyes.stream()
//                .map(FiberDyeResponse::new)
//                .toList();
//
//        return responseDTOs;
//    }
//
//    public void deleteFiberDye(Long id) throws ServiceException {
//        if (!fiberDyeRepository.existsById(id)) {
//            throw new RuntimeException("FiberDye not found with ID: " + id);
//        }
//        fiberDyeRepository.deleteById(id);
//    }
//
//    public FiberDyeResponse dyeFiber(FiberDyeRequest dto) throws ServiceException {
//
//        Order order = orderRepository.findById(dto.getOrderId())
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_ORDER));
//
//        ProcessDye processDye = dyeRepository.findById(dto.getProcessId())
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_PROCESS_RECEIVE));
//
//        FiberColor fiberColor = fiberColorRepository.findById(dto.getFiberColorId())
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_FIBER_COLOR));
//
//        FiberColor fiberOutColor = fiberColorRepository.findById(dto.getFiberColorId())
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_FIBER_COLOR));
//
//        double totalReceivedWeight = fiberDyeRepository.findByOrderId(dto.getOrderId()).stream()
//                .filter(fiberDye -> fiberDye.getProcessDye().getId().equals(dto.getProcessId()))
//                .mapToDouble(FiberDye::getRough_weight)
//                .sum();
//
//        if (totalReceivedWeight + dto.getRoughWeight() > order.getWeight()) {
//            throw new ServiceException(ErrorResponse.EXCEEDS_WEIGHT);
//        }
//
////        if (dto.getRoughWeight() > processDye.getAvailable_weight()) {
////            throw new ServiceException(ErrorResponse.EXCEEDS_AVAILABLE_WEIGHT);
////        }
//
//        int lastBaleNum = fiberDyeRepository.findByOrderId(dto.getOrderId()).stream()
//                .mapToInt(FiberDye::getBaleNum)
//                .max()
//                .orElse(0);
//
//        FiberDye fiberDye = new FiberDye();
//        fiberDye.setOrder(order);
//        fiberDye.setProcessDye(processDye);
//        fiberDye.setDateTime(LocalDateTime.now());
//        fiberDye.setColor(fiberColor);
//        fiberDye.setOut_color(fiberOutColor);
//        fiberDye.setRough_weight(dto.getRoughWeight());
//        fiberDye.setBale_weight(dto.getBaleWeight());
//        fiberDye.setCon_weight(dto.getConWeight());
//        fiberDye.setMoisture(dto.getMoisture());
//        fiberDye.setBaleNum(lastBaleNum + 1);
//
//        if (processDye.getFiberDyes() == null || processDye.getFiberDyes().isEmpty()) {
//            processDye.setStatus(ProcessStatus.IN_PROGRESS);
//        }
////
////        processDye.setTotal_weight(processDye.getTotal_weight() + dto.getRoughWeight());
////        processDye.setAvailable_weight(processDye.getAvailable_weight() - dto.getRoughWeight());
//
//        FiberDye savedFiberDye = fiberDyeRepository.save(fiberDye);
//        dyeRepository.save(processDye);
//
//        return new FiberDyeResponse(savedFiberDye);
//    }
//
//    public double getProcessedWeightInFiberReceive(Long orderId) {
//        return fiberReceiveService.getTotalWeightProcessed(orderId);
//    }
//
//    public Double getProcessedWeightInFiberDye(Long orderId) {
//        return fiberDyeRepository.findAll()
//                .stream()
//                .filter(fiberDye -> fiberDye.getOrder().getId().equals(orderId))
//                .mapToDouble(FiberDye::getRough_weight)
//                .sum();
//    }
//}