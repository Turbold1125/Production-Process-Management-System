package com.example.ProductionManagementSystem.Service.Const;

import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.FiberType;
import com.example.ProductionManagementSystem.Repo.Const.FiberTypeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FiberTypeService {

    private final FiberTypeRepository fiberTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(FiberTypeService.class);

    public List<FiberType> getAllFiberTypes() throws ServiceException {
        List<FiberType> types = fiberTypeRepository.findAll();
        if (types == null || types.isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_CONTENT);
        }
        return types;
    }

    public FiberType getFiberTypeById(Integer id) throws ServiceException {
        return fiberTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NOT_FOUND));
    }

    public FiberType saveFiberType(FiberType fiberType) throws ServiceException {
        if (fiberType == null || fiberType.getName() == null || fiberType.getName().isEmpty()) {
            throw new ServiceException(ErrorResponse.VALIDATION_ERROR);
        }

        logger.info("Saving fiber type: {}", fiberType.getName());

        return fiberTypeRepository.save(fiberType);
    }

    public void deleteFiberType(Integer id) throws ServiceException {
        if (!fiberTypeRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.NOT_FOUND);
        }
        fiberTypeRepository.deleteById(id);
    }

    public FiberType updateFiberType(Integer id, FiberType fiberType) throws ServiceException {

        FiberType existingFiberType = fiberTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NOT_FOUND));

        existingFiberType.setName(fiberType.getName());
        existingFiberType.setName_en(fiberType.getName_en());

        return fiberTypeRepository.save(existingFiberType);
    }
}
