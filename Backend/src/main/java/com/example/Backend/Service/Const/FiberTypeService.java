package com.example.Backend.Service.Const;

import com.example.Backend.Exception.ErrorResponse;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.FiberType;
import com.example.Backend.Repo.Const.FiberTypeRepository;
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

    public FiberType getFiberTypeById(Long id) throws ServiceException {
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

    public void deleteFiberType(Long id) throws ServiceException {
        if (!fiberTypeRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.NOT_FOUND);
        }
        fiberTypeRepository.deleteById(id);
    }

    public FiberType updateFiberType(Long id, FiberType fiberType) throws ServiceException {

        FiberType existingFiberType = fiberTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NOT_FOUND));

        existingFiberType.setName(fiberType.getName());
        existingFiberType.setName_en(fiberType.getName_en());

        return fiberTypeRepository.save(existingFiberType);
    }
}
