package com.example.Backend.Controller.Const;

import com.example.Backend.Api.Const.MaterialApi;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.Material;
import com.example.Backend.Service.Const.MaterialService;
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
    public Material getMaterialById(@PathVariable Long id) throws ServiceException {
        return materialService.getMaterialById(id);
    }

    @PostMapping("/create")
    public Material createMaterial(@RequestBody Material material) throws ServiceException {
        return materialService.saveMaterial(material);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMaterial(@PathVariable Long id) throws ServiceException {
        materialService.deleteMaterial(id);
    }

    @PutMapping("/update/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material material) throws ServiceException {
        return materialService.updateMaterial(id, material);
    }
}
