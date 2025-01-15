package com.example.ProductionManagementSystem.Service.Const;

import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.FactoryProcess;
import com.example.ProductionManagementSystem.Repo.Const.FactoryProcessRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactoryProcessService {

    private final FactoryProcessRepository factoryProcessRepository;

    private static final Logger logger = LoggerFactory.getLogger(FactoryProcessService.class);

    public List<FactoryProcess> getAllProcess() throws ServiceException {
        List<FactoryProcess> processList = factoryProcessRepository.findAll();
        logger.info("Getting all factory processes {}", processList.size(), processList);
        if (processList.isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_CONTENT);
        }

        return processList;
    }

    public FactoryProcess getProcessById(Integer id) throws ServiceException {
        logger.info("Getting factory process by id: {}", id);
        FactoryProcess process = factoryProcessRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NOT_FOUND));
        logger.info("Factory process name: {} and description: {}", process.getName(), process.getDescription());
        return process;
    }

    public FactoryProcess saveProcess(FactoryProcess factoryProcess) throws ServiceException {
        if (factoryProcess == null || factoryProcess.getName() == null || factoryProcess.getName().isEmpty()) {
            throw new ServiceException(ErrorResponse.VALIDATION_ERROR);
        }
        logger.info("Saving factory process: {}", factoryProcess.getName());

        return factoryProcessRepository.save(factoryProcess);
    }

    public void deleteProcess(Integer id) throws ServiceException {
        if (!factoryProcessRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.NOT_FOUND);
        }
        factoryProcessRepository.deleteById(id);
    }

    public FactoryProcess updateProcess(Integer id, FactoryProcess factoryProcess) throws ServiceException {

        FactoryProcess existingFactoryProcess = factoryProcessRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NOT_FOUND));

        existingFactoryProcess.setName(factoryProcess.getName());
        existingFactoryProcess.setDescription(factoryProcess.getDescription());

        return factoryProcessRepository.save(existingFactoryProcess);
    }
}
