//package com.example.Backend.Controller.Process;
//
//import com.example.Backend.Api.Process.FiberBlendApi;
//import com.example.Backend.DTOs.Process.FiberBlendResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Service.Fiber.FiberBlendService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/fiber-blend")
//@RequiredArgsConstructor
//public class FiberBlendController implements FiberBlendApi {
//
//    private final FiberBlendService fiberBlendService;
//
//    @GetMapping
//    public List<FiberBlendResponse> getAllFiberBlends() throws ServiceException {
//        return fiberBlendService.getAllFiberBlends();
//    }
//
//    @GetMapping("/id")
//    public FiberBlendResponse getFiberBlendById(@PathVariable Long id) throws ServiceException {
//        return fiberBlendService.getFiberBlendById(id);
//    }
//
////    @PostMapping("/process/{orderId}")
////    public FiberBlendResponse processFiberBlend(@PathVariable Long orderId, @RequestBody FiberBlendRequest dto) throws ServiceException {
////        return fiberBlendService.processFiberBlend(orderId, dto);
////    }
//
//    @DeleteMapping("/id")
//    public void deleteFiberBlend(@PathVariable Long id) throws ServiceException {
//        fiberBlendService.deleteFiberBlend((id));
//    }
//
//    @GetMapping("/order-id/{orderId}")
//    public List<FiberBlendResponse> getFiberBlendsByOrderId(@PathVariable Long orderId) throws ServiceException {
//        return fiberBlendService.getAllFiberBlendsByOrderId(orderId);
//    }
//
//    @Override
//    public double getProcessedWeightInFiberReceive(@PathVariable Long orderId) throws ServiceException {
//        return 0;
//    }
//
//    @Override
//    public double getProcessedWeightInFiberDye(@PathVariable Long orderId) throws ServiceException {
//        return 0;
//    }
//}
