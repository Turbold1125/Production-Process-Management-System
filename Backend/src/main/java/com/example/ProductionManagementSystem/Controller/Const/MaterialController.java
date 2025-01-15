package com.example.ProductionManagementSystem.Controller.Const;

import com.example.ProductionManagementSystem.Api.Const.MaterialApi;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.Material;
import com.example.ProductionManagementSystem.Service.Const.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material")
@RequiredArgsConstructor
public class MaterialController implements MaterialApi {

    private final MaterialService materialService;

    @GetMapping
    public List<Material> getAllMaterial() throws ServiceException {
        return materialService.getAllMaterial();
    }

    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable Integer id) throws ServiceException {
        return materialService.getMaterialById(id);
    }

    @PostMapping("/create")
    public Material createMaterial(@RequestBody Material material) throws ServiceException {
        return materialService.saveMaterial(material);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMaterial(@PathVariable Integer id) throws ServiceException {
        materialService.deleteMaterial(id);
    }

    @PutMapping("/update/{id}")
    public Material updateMaterial(@PathVariable Integer id, @RequestBody Material material) throws ServiceException {
        return materialService.updateMaterial(id, material);
    }
}
