//package com.example.Backend.Controller;
//
//import com.example.Backend.DTOs.CompleteDyeingProcessRequest;
//import com.example.Backend.DTOs.DyeingProcessRequest;
//import com.example.Backend.Service.DyeingProcessService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/dyeing")
//@RequiredArgsConstructor
//public class DyeingController {
//
//    private final DyeingProcessService dyeingProcessService;
//
//    @PostMapping("/start")
//    public ResponseEntity<String> startDyeingProcess(@RequestBody DyeingProcessRequest request) {
//        dyeingProcessService.startDyeingProcess(
//            request.getMaterial(),
//                request.getInputWeight(),
//                request.getFiberType(),
//                request.getColor(),
//                request.getCustomer(),
//                request.getOrderId()
//        );
//        return ResponseEntity.ok("Dyeing process started successfully");
//    }
//
//    @PostMapping("/complete")
//    public ResponseEntity<String> completeDyeingProcess(@RequestBody CompleteDyeingProcessRequest request) {
//        dyeingProcessService.completeDyeingProcess(request.getDyeingProcessId(), request.getOutputWeight());
//        return ResponseEntity.ok("Dyeing process completed successfully");
//    }
//}