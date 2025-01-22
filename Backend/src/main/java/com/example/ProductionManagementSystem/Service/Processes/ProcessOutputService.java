package com.example.ProductionManagementSystem.Service.Processes;

import com.example.ProductionManagementSystem.Constants.ProcessStatus;
import com.example.ProductionManagementSystem.DTOs.Process.FiberRequest;
import com.example.ProductionManagementSystem.DTOs.Process.WasteRequest;
import com.example.ProductionManagementSystem.Model.Const.OutputType;
import com.example.ProductionManagementSystem.Model.Inventory;
import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Model.Processes.ProcessInput;
import com.example.ProductionManagementSystem.Model.Processes.ProcessOutput;
import com.example.ProductionManagementSystem.Repo.ProcessRepository;
import com.example.ProductionManagementSystem.Repo.Processes.ProcessInputRepository;
import com.example.ProductionManagementSystem.Repo.Processes.ProcessOutputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessOutputService {

    private final ProcessInputRepository processInputRepository;
    private final ProcessRepository processRepository;
    private final ProcessOutputRepository processOutputRepository;

    public void logProcessInput(Process process, FiberRequest fiber, Inventory inventory) {
        ProcessInput processInput = new ProcessInput(
                process.getId(),
                process.getProcessName(),
                process.getOrderId(),
                fiber,
                inventory,
                process.getCustomerName()
        );
        processInputRepository.save(processInput);
    }

    public void logPrimaryOutput(Process process, Inventory inventory, OutputType outputType) {
        ProcessOutput output = new ProcessOutput(
                process.getId(),
                process.getProcessName(),
                process.getOrderId(),
                inventory,
                outputType
        );
        processOutputRepository.save(output);
    }

    public void logWasteOutput(Process process, Inventory inventory, WasteRequest wasteRequest, OutputType outputType) {
        ProcessOutput output = new ProcessOutput(
                process.getId(),
                process.getProcessName(),
                process.getOrderId(),
                inventory,
                wasteRequest.getMaterial(),
                wasteRequest.getWeight(),
                outputType
        );
        processOutputRepository.save(output);
    }

    public boolean areAllProcessesCompleted(Integer orderId) {
        List<Process> processes = processRepository.findByOrderId(orderId);
        return processes.stream().allMatch(p -> p.getStatus() == ProcessStatus.COMPLETED);
    }

    public String checkInputColor(Integer orderId, String firstColor) {
        List<Process> previousProcesses = processRepository.findByOrderId(orderId);
        previousProcesses.sort(Comparator.comparingInt(Process::getId));
        return previousProcesses.stream()
                .filter(p -> p.getStatus() == ProcessStatus.COMPLETED)
                .map(Process::getOutputMaterialColor)
                .reduce((first, second) -> second)
                .orElse(firstColor);
    }

    public List<ProcessOutput> getProcessOutputsByOrderId(Integer orderId) {
        return processOutputRepository.findByOrderId(orderId);
    }

    public List<ProcessInput> getProcessInputsByOrderId(Integer orderId) {
        return processInputRepository.findByOrderId(orderId);
    }
}
