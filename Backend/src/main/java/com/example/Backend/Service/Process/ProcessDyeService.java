//package com.example.Backend.Service.Process;
//
//import com.example.Backend.Constants.ProcessStatus;
//import com.example.Backend.Exception.ErrorResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Model.Const.FiberColor;
//import com.example.Backend.Model.Const.MaterialEnum;
//import com.example.Backend.Model.Fibers.FiberDyed;
//import com.example.Backend.Model.Inventory;
//import com.example.Backend.Model.Process.ProcessDye;
//import com.example.Backend.Repo.Fibers.FiberDyeRepository;
//import com.example.Backend.Repo.InventoryRepository;
//import com.example.Backend.Repo.Process.DyeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ProcessDyeService {
//
//    private final InventoryRepository inventoryRepository;
//    private final FiberDyeRepository fiberDyedRepository;
//    private final DyeRepository processDyeRepository;
//
//    public ProcessDye startDyeing(Long processId, double inputWeight, FiberColor outputColor) throws ServiceException {
//        // Validate the process
//        ProcessDye processDye = processDyeRepository.findById(processId)
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_PROCESS));
//
//        // Check inventory for sufficient received fiber
//        Inventory inventory = inventoryRepository.findByCustomerAndMaterialAndColorAndFiberType(
//                        processDye.getOrder().getCustomer(), MaterialEnum.RAW_MATERIAL, null, null)
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_INVENTORY));
//
//        if (inventory.getConWeight() < inputWeight) {
//            throw new ServiceException(ErrorResponse.INSUFFICIENT_INVENTORY);
//        }
//
//        // Deduct the input weight from inventory
//        inventory.setConWeight(inventory.getConWeight() - inputWeight);
//        inventoryRepository.save(inventory);
//
//        // Update process status and start time
//        processDye.setDateStart(LocalDateTime.now());
//        processDye.setStatus(ProcessStatus.IN_PROGRESS);
//        processDyeRepository.save(processDye);
//
//        return processDye;
//    }
//
//    public void completeDyeing(Long processId, List<FiberDyed> dyedFibers) throws ServiceException {
//        ProcessDye processDye = processDyeRepository.findById(processId)
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_PROCESS));
//
//        // Save output fibers and register them in inventory
//        for (FiberDyed fiberDyed : dyedFibers) {
//            fiberDyed.setProcessDye(processDye);
//            fiberDyed.setDateTime(LocalDateTime.now());
//            fiberDyedRepository.save(fiberDyed);
//
//            // Add output to inventory
//            Inventory inventory = new Inventory();
//            inventory.setCustomer(processDye.getOrder().getCustomer());
////            inventory.setMaterial(MaterialEnum.DYED_FIBER); // Assuming material type enum or identifier
//            inventory.setConWeight(fiberDyed.getCon_weight());
//            inventoryRepository.save(inventory);
//        }
//
//        // Mark process as completed
//        processDye.setDateEnd(LocalDateTime.now());
//        processDye.setStatus(ProcessStatus.COMPLETED);
//        processDyeRepository.save(processDye);
//    }
//}