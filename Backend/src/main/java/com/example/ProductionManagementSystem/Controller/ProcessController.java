//package com.example.Change.Controller;
//
//import com.example.Change.Api.ProcessApi;
//import com.example.Change.DTOs.ProcessRequest;
//import com.example.Change.Model.Inventories;
//import com.example.Change.Model.Process;
//import com.example.Change.Service.InventoriesService;
//import com.example.Change.Service.ProcessService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/processes")
//@RequiredArgsConstructor
//public class ProcessController implements ProcessApi {
//
//    private final ProcessService processService;
//    private final InventoriesService inventoriesService;
//
//    @PostMapping("/start")
//    public Process startProcess(@RequestBody ProcessRequest request) {
//        return processService.startProcess(request);
//    }
//
//    @PostMapping("/end")
//    public Inventories endProcess(@RequestParam Integer processId, @RequestBody ProcessRequest request) {
//        return processService.endProcess(processId, request);
//    }
//}

package com.example.ProductionManagementSystem.Controller;

import com.example.ProductionManagementSystem.Api.ProcessApi;
import com.example.ProductionManagementSystem.Constants.ProcessStatus;
import com.example.ProductionManagementSystem.DTOs.EndProcessRequest;
import com.example.ProductionManagementSystem.DTOs.ProcessRequest;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Inventory;
import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Model.ProcessLog;
import com.example.ProductionManagementSystem.Model.Processes.ProcessInput;
import com.example.ProductionManagementSystem.Model.Processes.ProcessOutput;
import com.example.ProductionManagementSystem.Service.ProcessService;
import com.example.ProductionManagementSystem.Service.Processes.ProcessOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processes")
@RequiredArgsConstructor
public class ProcessController implements ProcessApi {

    private final ProcessService processService;
    private final ProcessOutputService processOutputService;

    @GetMapping("/tasks/{userId}")
    public List<Process> getProcessesByUserId(@PathVariable Integer userId) {
        return processService.getProcessesByUserId(userId);
    }

    @GetMapping("/required-materials")
    public ResponseEntity<List<Inventory>> getRequiredMaterials(
            @RequestParam String processName,
            @RequestParam String customerName) {
        List<Inventory> materials = processService.getRequiredMaterialsForProcess(processName, customerName);
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/latest/logs")
    public List<ProcessLog> getLatestLogs() {
        return processService.getLatestProcessLogs();
    }

    @PostMapping("/start")
    public Process startProcess(@RequestBody ProcessRequest request)  throws ServiceException {
        return processService.startProcess(request);
    }

    @PostMapping("/end")
    public Inventory endProcess(@RequestBody EndProcessRequest request) throws ServiceException {
        return processService.endProcess(request);
    }

    @GetMapping("/all")
    public List<ProcessLog> getAllProcessLogs() {
        return processService.getAllProcessLogs();
    }

    @GetMapping("/log/{orderId}")
    public List<ProcessLog> getProcessLogsForOrder(@PathVariable Integer orderId) {
        return processService.getProcessLogsForOrder(orderId);
    }

    @GetMapping("/log/active")
    public List<Process> getActiveProcessLogs() {
        return processService.getAllActiveProcesses();
    }

    @GetMapping("/customer/{customerName}")
    public List<Process> getProcessLogsForCustomer(@PathVariable String customerName) {
        return processService.getProcessesByCustomer(customerName);
    }

    @GetMapping("/status/{status}")
    public List<Process> getProcessesByStatus(@PathVariable ProcessStatus status) {
        return processService.getProcessesByStatus(status);
    }

    @GetMapping("/processOutputs/{orderId}")
    public List<ProcessOutput> getProcessOutputs(@PathVariable Integer orderId) {
        return processOutputService.getProcessOutputsByOrderId(orderId);
    }

    @GetMapping("/processInputs/{orderId}")
    public List<ProcessInput> getProcessInputs(@PathVariable Integer orderId) {
        return processOutputService.getProcessInputsByOrderId(orderId);
    }
}