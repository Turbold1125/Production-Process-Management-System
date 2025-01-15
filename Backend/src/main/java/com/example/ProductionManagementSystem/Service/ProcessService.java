    package com.example.ProductionManagementSystem.Service;
    
    import com.example.ProductionManagementSystem.Constants.ProcessStatus;
    import com.example.ProductionManagementSystem.DTOs.EndProcessRequest;
    import com.example.ProductionManagementSystem.DTOs.FiberRequest;
    import com.example.ProductionManagementSystem.DTOs.ProcessRequest;
    import com.example.ProductionManagementSystem.DTOs.WasteRequest;
    import com.example.ProductionManagementSystem.Exception.ErrorResponse;
    import com.example.ProductionManagementSystem.Exception.ServiceException;
    import com.example.ProductionManagementSystem.Model.Const.FactoryProcess;
    import com.example.ProductionManagementSystem.Model.Const.OutputType;
    import com.example.ProductionManagementSystem.Model.Const.Status;
    import com.example.ProductionManagementSystem.Model.Inventory;
    import com.example.ProductionManagementSystem.Model.Process;
    import com.example.ProductionManagementSystem.Model.ProcessLog;
    import com.example.ProductionManagementSystem.Model.User.User;
    import com.example.ProductionManagementSystem.Repo.Const.FactoryProcessRepository;
    import com.example.ProductionManagementSystem.Repo.InventoryRepository;
    import com.example.ProductionManagementSystem.Repo.ProcessLogRepository;
    import com.example.ProductionManagementSystem.Repo.ProcessRepository;
    import com.example.ProductionManagementSystem.Service.Processes.ProcessOutputService;
    import com.example.ProductionManagementSystem.Service.User.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    
    import java.time.LocalDateTime;
    import java.util.*;
    
    @Service
    @RequiredArgsConstructor
    public class ProcessService {
    
        private final ProcessRepository processRepository;
        private final InventoryRepository inventoryRepository;
        private final ProcessLogRepository processLogRepository;
        private final FactoryProcessRepository factoryProcessRepository;
        private final UserService userService;
        private final InventoryService inventoryService;
        private final ProcessOutputService processOutputService;
    
        private final OrderService orderService;
    
        public List<Process> getProcessesByUserId(Integer userId) {
            return processRepository.findByUserId(userId);
        }
    
        public List<Inventory> getRequiredMaterialsForProcess(String processName, String customerName) {
    
            FactoryProcess factoryProcess = factoryProcessRepository.findByName(processName);
    
            List<String> requiredMaterials = Arrays.asList(factoryProcess.getInputs().split(",\\s*"));
    
            return inventoryRepository.findByCustomerNameAndFiberMaterialIn(customerName, requiredMaterials);
        }
    
        public List<Process> getProcessesByOrderId(Integer orderId) {
    
            List<Process> processes = processRepository.findByOrderId(orderId);
            processes.sort(Comparator.comparingInt(Process::getId));
            return processes;
        }
    
        public Process startProcess(ProcessRequest request) throws ServiceException {
    
            Process process = processRepository.findByOrderIdAndProcessName(request.getOrderId(), request.getProcessName());

            if (process == null) {
                throw new ServiceException(ErrorResponse.NO_PROCESS);
            }

            if (process.getStatus().name().equals(Status.IN_PROGRESS)) {
                throw new ServiceException(ErrorResponse.IN_PROGRESS);
            }

            User user = userService.getUserById(request.getUserId());
    
            double totalInputWeight = 0;
            String firstColor = null;
    
            Set<String> uniqueMaterials = new LinkedHashSet<>();
    
            for (FiberRequest fiber : request.getFibers()) {
                Inventory inventory = inventoryService.validateAndDeductInventory(fiber.getInventoryId(), fiber.getInputMaterialWeight(), request.getCustomerName(), request.getProcessName());
    
    //            processOutputService.logProcessInput(process.getId(), request.getOrderId(), fiber, inventory, request.getCustomerName());
    
                processOutputService.logProcessInput(process, fiber, inventory);
    
                totalInputWeight += fiber.getInputMaterialWeight();
    
                uniqueMaterials.add(fiber.getInputMaterial());
    
                firstColor = inventory.getFiberColor();
            }
    
            String inputColor = processOutputService.checkInputColor(request.getOrderId(), firstColor);
    
            String inputMaterials = String.join(", ", uniqueMaterials);
    
            process.setUserId(request.getUserId());
            process.setUsername(user.getUsername());
            process.setInputMaterial(inputMaterials);
            process.setInputMaterialColor(inputColor);
            process.setInputMaterialWeight(totalInputWeight);
            process.setStatus(ProcessStatus.IN_PROGRESS);
            process.setDateTime(LocalDateTime.now());
            processRepository.save(process);
    
            logProcessAction(process, Status.START.name(), request.getUserId(), user.getUsername());
    
            orderService.updateOrderStatus(request.getOrderId(), Status.IN_PROGRESS);
    
            return process;
        }
    
        public Inventory endProcess(EndProcessRequest request) throws ServiceException {
    
            if (request.getProcessId() == null) {
                throw new IllegalArgumentException("Process ID cannot be null.");
            }
    
            Process process = processRepository.findById(request.getProcessId())
                    .orElseThrow(() -> new RuntimeException("Process not found."));
    
            User user = userService.getUserById(request.getUserId());
    
            if(request.getOutputWeight() <= 0) {
                throw new IllegalArgumentException("Output weight must be greater than 0.");
            }
    
            process.setStatus(ProcessStatus.COMPLETED);
            process.setUserId(request.getUserId());
            process.setUsername(user.getUsername());
            process.setOutputMaterialWeight(request.getOutputWeight());
            process.setOutputMaterial(request.getOutputMaterial());
            process.setOutputMaterialColor(request.getOutputColor());
            processRepository.save(process);
    
            logProcessAction(process, Status.COMPLETED.name(), request.getUserId(), user.getUsername());

            if (request.getOutputMaterial() == null || request.getOutputMaterial().isEmpty()) {
                throw new IllegalArgumentException("Output material cannot be null or empty.");
            }
    
            Inventory outputInventory = inventoryService.createProcessedInventory(request.getOutputMaterial(), request.getOutputColor(), request.getOutputWeight(), process.getCustomerName(), request.getFiberType());
    
            processOutputService.logPrimaryOutput(process, outputInventory, OutputType.PRIMARY);
    
            if (request.getWastes() != null && !request.getWastes().isEmpty()) {
                for (WasteRequest wasteRequest : request.getWastes()) {
                    if (wasteRequest.getWeight() > 0) {
                        Inventory wasteInventory = inventoryService.createWasteInventory(
                                wasteRequest.getMaterial(),
                                wasteRequest.getWeight(),
                                process.getCustomerName(),
                                request.getOutputColor()
                        );
    
                        processOutputService.logWasteOutput(process, wasteInventory, wasteRequest, OutputType.WASTE);
                    }
                }
            }
            boolean allCompleted = processOutputService.areAllProcessesCompleted(process.getOrderId());
            if (allCompleted) {
                orderService.updateOrderStatus(process.getOrderId(), Status.COMPLETED);
            }
    
            return outputInventory;
        }
    
        public void logProcessAction(Process process, String action, Integer userId, String username) {
            ProcessLog log = processLogRepository.findByOrderIdAndProcessName(process.getOrderId(), process.getProcessName())
                    .orElse(new ProcessLog());
    
            if (log.getId() == null) {
                log.setProcessName(process.getProcessName());
                log.setOrderId(process.getOrderId());
                log.setUserId(userId);
                log.setUsername(username);
                log.setInputMaterial(process.getInputMaterial());
                log.setInputMaterialWeight(process.getInputMaterialWeight());
                log.setInputMaterialColor(process.getInputMaterialColor());
            } else {
                log.setUserId(userId);
                log.setUsername(username);
                log.setOutputMaterial(process.getOutputMaterial());
                log.setOutputMaterialWeight(process.getOutputMaterialWeight());
                log.setOutputMaterialColor(process.getOutputMaterialColor());
    
    //            List<ProcessOutput> wasteOutputs = processOutputRepository.findByProcessIdAndType(process.getId(), OutputType.WASTE);
    //            if (!wasteOutputs.isEmpty()) {
    //                String wasteSummary = wasteOutputs.stream()
    //                        .map(waste -> waste.getMaterial() + ": " + waste.getWeight() + "кг")
    //                        .reduce((w1, w2) -> w1 + ", " + w2)
    //                        .orElse("No waste");
    //                log.setWasteDetails(wasteSummary);
    //            }
            }
    
            if (action.equals(Status.START.name())) {
                log.setProcessStartTime(LocalDateTime.now());
            } else if (action.equals(Status.COMPLETED.name())) {
                log.setProcessEndTime(LocalDateTime.now());
            }
    
            log.setUpdatedTime(LocalDateTime.now());
    
            processLogRepository.save(log);
        }
    
        public List<ProcessLog> getLatestProcessLogs() {
            List<ProcessLog> allLogs = processLogRepository.findAllByOrderByUpdatedTimeDesc();
            return allLogs.size() > 50 ? allLogs.subList(0, 50) : allLogs;
        }
    
        public List<ProcessLog> getAllProcessLogs() {
            return processLogRepository.findAll();
        }
    
        public List<ProcessLog> getProcessLogsForOrder(Integer orderId) {
            return processLogRepository.findByOrderId(orderId);
        }
    
        public Process updateProcessStatus(Integer processId, ProcessStatus status) {
            Process process = processRepository.findById(processId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid process ID"));
            process.setStatus(status);
            return processRepository.save(process);
        }
    
        public List<Process> getAllActiveProcesses() {
            return processRepository.findByStatus(ProcessStatus.IN_PROGRESS);
        }
    
        public Process getProcessById(Integer processId) {
            return processRepository.findById(processId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid process ID"));
        }
    
        public List<Process> getProcessesByCustomer(String customerName) {
            return processRepository.findByCustomerName(customerName);
        }
    
        public List<Process> getProcessesByStatus(ProcessStatus status) {
            return processRepository.findByStatus(status);
        }
    }