package com.example.Backend.Service.Const;

import com.example.Backend.Exception.ErrorResponse;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Repo.Const.FiberColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FiberColorService {

    private final FiberColorRepository fiberColorRepository;

    public List<FiberColor> getAllFiberColors() throws ServiceException {
        List<FiberColor> colors = fiberColorRepository.findAll();

        if(colors == null || colors.isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_CONTENT);
        }

        return colors;
    }

    public FiberColor getColorById(Long id) throws ServiceException {
        FiberColor color = fiberColorRepository.findById(id).orElse(null);

        if(color == null) {
            throw new ServiceException(ErrorResponse.COLOR_NOT_FOUND);
        }

        return color;
    }

    public FiberColor saveFiberColor(FiberColor fiberColor) throws ServiceException{
        return fiberColorRepository.save(fiberColor);
    }

    public void deleteFiberColor(Long id) throws ServiceException{
        if (!fiberColorRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.COLOR_NOT_FOUND);
        }
        fiberColorRepository.deleteById(id);
    }

    public FiberColor updateFiberColor(Long id, FiberColor fiberColor) throws ServiceException {
        FiberColor existingFiberColor = fiberColorRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.COLOR_NOT_FOUND));

        existingFiberColor.setName(fiberColor.getName());
        existingFiberColor.setName_en(fiberColor.getName_en());

        return fiberColorRepository.save(existingFiberColor);
    }
}
