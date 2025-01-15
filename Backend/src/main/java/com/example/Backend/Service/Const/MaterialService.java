package com.example.Backend.Service.Const;

import com.example.Backend.Exception.ErrorResponse;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.Material;
import com.example.Backend.Repo.Const.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;


    private static final Logger logger = LoggerFactory.getLogger(FiberTypeService.class);

    public List<Material> getAllMaterial() throws ServiceException {
        return materialRepository.findAll();
    }

    public Material getMaterialById(Long id) throws ServiceException {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NOT_FOUND));
    }

    public Material saveMaterial(Material material) throws ServiceException {
        if (material == null || material.getName() == null || material.getName().isEmpty()) {
            throw new ServiceException(ErrorResponse.VALIDATION_ERROR);
        }

        logger.info("Saving material: {}", material.getName());

        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) throws ServiceException {
        if (!materialRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.NOT_FOUND);
        }
        materialRepository.deleteById(id);
    }

    public Material updateMaterial(Long id, Material material) throws ServiceException {
        Material existingMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NOT_FOUND));

        existingMaterial.setName(material.getName());
        existingMaterial.setName_en(material.getName_en());

        return materialRepository.save(existingMaterial);
    }
}
