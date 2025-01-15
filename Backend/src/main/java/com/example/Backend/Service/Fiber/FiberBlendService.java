//package com.example.Backend.Service.Fiber;
//
//import com.example.Backend.DTOs.Process.FiberBlendResponse;
//import com.example.Backend.Exception.ErrorResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Model.Fibers.FiberBlend;
//import com.example.Backend.Repo.Const.FiberBlendRepository;
//import com.example.Backend.Repo.Const.FiberColorRepository;
//import com.example.Backend.Repo.OrderRepository;
//import com.example.Backend.Repo.Fibers.FiberDyeRepository;
//import com.example.Backend.Repo.Fibers.FiberReceiveRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FiberBlendService {
//
//    private final FiberBlendRepository fiberBlendRepository;
//    private final FiberColorRepository fiberColorRepository;
//    private final OrderRepository orderRepository;
//    private final FiberDyeRepository fiberDyeRepository;
//    private final FiberReceiveRepository fiberReceiveRepository;
//
//    private final FiberReceiveService fiberReceiveService;
//    private final FiberDyeService fiberDyeService;
//
//    public List<FiberBlendResponse> getAllFiberBlends() throws ServiceException {
//        List<FiberBlend> fiber = fiberBlendRepository.findAll();
//
//        if (fiber == null || fiber.isEmpty()) {
//            throw new ServiceException(ErrorResponse.NO_CONTENT);
//        }
//
//        List<FiberBlendResponse> fiberBlendResponses =  new ArrayList<>();
//        for (FiberBlend fiberBlend : fiber) {
//            fiberBlendResponses.add(new FiberBlendResponse(fiberBlend));
//        }
//        return fiberBlendResponses;
//    }
//
//    public FiberBlendResponse getFiberBlendById(Long id) throws ServiceException {
//        FiberBlend fiberBlend = fiberBlendRepository.findById(id)
//               .orElseThrow(() -> new RuntimeException("Fiber blend not found with ID: " + id));
//        return new FiberBlendResponse(fiberBlend);
//    }
//
//    public List<FiberBlendResponse> getAllFiberBlendsByOrderId(Long orderId) throws ServiceException {
//        List<FiberBlend> fiberBlends = fiberBlendRepository.findByOrderId(orderId);
//
//        if (fiberBlends == null || fiberBlends.isEmpty()) {
//            throw new ServiceException(ErrorResponse.NO_CONTENT);
//        }
//
//        // Map the results to FiberResponse DTOs
//        List<FiberBlendResponse> responseDTOs = fiberBlends.stream()
//                .map(FiberBlendResponse::new)
//                .toList();
//
//        return responseDTOs;
//    }
//
//    public void deleteFiberBlend(Long id) throws ServiceException {
//        if (!fiberBlendRepository.existsById(id)) {
//            throw new RuntimeException("FiberDye not found with ID: " + id);
//        }
//        fiberBlendRepository.deleteById(id);
//    }
//}
