//package com.example.Backend.Service.Process;
//
//import com.example.Backend.Constants.ProcessStatus;
//import com.example.Backend.Exception.ErrorResponse;
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Model.Process.ProcessDye;
//import com.example.Backend.Model.Process.ProcessReceive;
//import com.example.Backend.Repo.Process.ReceiveRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class ReceiveProcessService {
//
//    private final ReceiveRepository receiveRepository;
//
//    public ProcessReceive getProcessReceiveById(Long processId) throws ServiceException {
//        return receiveRepository.findById(processId)
//                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_PROCESS_RECEIVE));
//    }
//
//    public void updateProcessReceiveWeights(ProcessReceive processReceive, double roughWeight, ProcessDye processDye) throws ServiceException{
//        double updatedTotalWeight = processReceive.getTotal_weight() + roughWeight;
//        double updatedAvailableWeight = processReceive.getAvailable_weight() - roughWeight;
//
//        processReceive.setTotal_weight(updatedTotalWeight);
//        processReceive.setAvailable_weight(updatedAvailableWeight);
//
//        if (updatedAvailableWeight == 0) {
//            processReceive.setStatus(ProcessStatus.COMPLETED);
//            processReceive.setDateEnd(LocalDateTime.now());
//        }
//
//        receiveRepository.save(processReceive);
//    }
//
////    public void updateProcessDyeWeights(ProcessDye processDye, double roughWeight) {
////        processDye.setAvailable_weight(processDye.getAvailable_weight() + roughWeight);
////    }
//
//    public void setProcessReceiveInProgress(ProcessReceive processReceive) {
//        if (processReceive.getFiberReceives() == null || processReceive.getFiberReceives().isEmpty()) {
//            processReceive.setStatus(ProcessStatus.IN_PROGRESS);
//            processReceive.setDateStart(LocalDateTime.now());
//        }
//    }
//}
