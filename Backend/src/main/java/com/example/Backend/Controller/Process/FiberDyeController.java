//package com.example.Backend.Controller.Process;
//
//
//import com.example.Backend.Api.Process.FiberDyeApi;
//import com.example.Backend.DTOs.Process.FiberDyeRequest;
//import com.example.Backend.DTOs.Process.FiberDyeResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Service.Fiber.FiberDyeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/fiber-dye")
//@RequiredArgsConstructor
//public class FiberDyeController implements FiberDyeApi {
//
//    private final FiberDyeService fiberDyeService;
//
//    @GetMapping
//    public List<FiberDyeResponse> getAllFiberDyes() throws ServiceException {
//        return fiberDyeService.getAllFiberDyes();
//    }
//
//    @GetMapping("/id")
//    public FiberDyeResponse getFiberReceiveById(@PathVariable Long id) throws ServiceException {
//        return fiberDyeService.getFiberDyeById(id);
//    }
//
////    @PostMapping("/process/{orderId}")
////    public FiberDyeResponse processFiberDye(@PathVariable Long orderId, @RequestBody FiberDyeRequest dto) throws ServiceException {
////        return fiberDyeService.processFiberDye(orderId, dto);
////    }
//
//    @PostMapping
//    public FiberDyeResponse dyeFiber(@RequestBody FiberDyeRequest dto) throws ServiceException {
//        return fiberDyeService.dyeFiber((dto));
//    }
//
//    @DeleteMapping("/id")
//    public void deleteFiberReceive(@PathVariable Long id) throws ServiceException {
//        fiberDyeService.deleteFiberDye((id));
//    }
//
//    @GetMapping("/order-id/{orderId}")
//    public List<FiberDyeResponse> getFiberDyesByOrderId(@PathVariable Long orderId) throws ServiceException {
//        return fiberDyeService.getAllFiberDyesByOrderId(orderId);
//    }
//
//    @GetMapping("/fiber-receive/processed-weight/{orderId}")
//    public double getProcessedWeightInFiberReceive(@PathVariable Long orderId) throws ServiceException {
//        return fiberDyeService.getProcessedWeightInFiberReceive(orderId);
//    }
//
//    @GetMapping("/fiber-dye/processed-weight/{orderId}")
//    public double getProcessedWeightInFiberDye(@PathVariable Long orderId) throws ServiceException {
//        return fiberDyeService.getProcessedWeightInFiberDye(orderId);
//    }
//}
