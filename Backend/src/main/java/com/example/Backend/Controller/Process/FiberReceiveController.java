//package com.example.Backend.Controller.Process;
//
//import com.example.Backend.Api.Process.FiberReceiveApi;
//import com.example.Backend.DTOs.Process.FiberReceiveRequest;
//import com.example.Backend.DTOs.Process.FiberReceiveResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Service.Fiber.FiberReceiveService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/fiber-receive")
//@RequiredArgsConstructor
//public class FiberReceiveController implements FiberReceiveApi {
//
//    private final FiberReceiveService fiberReceiveService;
//
//
//    @GetMapping
//    public List<FiberReceiveResponse> getAllFiberReceives() throws ServiceException {
//        return fiberReceiveService.getAllFiberReceives();
//    }
//
//    @GetMapping("/{id}")
//    public FiberReceiveResponse getFiberReceiveById(@PathVariable Long id) throws ServiceException {
//        return fiberReceiveService.getFiberReceiveById(id);
//    }
//
//    @GetMapping("/order-id/{orderId}")
//    public List<FiberReceiveResponse> getFiberReceivesByOrderId(@RequestParam Long orderId) throws ServiceException {
//        return fiberReceiveService.getAllFiberReceivesByOrderId(orderId);
//    }
//
//    @PostMapping
//    public FiberReceiveResponse createFiberReceive(@RequestBody FiberReceiveRequest dto) throws ServiceException {
//        return fiberReceiveService.createFiberReceive(dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteFiberReceive(@PathVariable Long id) throws ServiceException {
//        fiberReceiveService.deleteFiberReceive(id);
//    }
//}
//
