package com.example.ProductionManagementSystem.Controller.Const;

import com.example.ProductionManagementSystem.Api.Const.FiberColorApi;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.FiberColor;
import com.example.ProductionManagementSystem.Service.Const.FiberColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fiberColor")
@RequiredArgsConstructor
public class FiberColorController implements FiberColorApi {

    private final FiberColorService fiberColorService;

    @GetMapping
    public List<FiberColor> getAllColors() throws ServiceException {
        return fiberColorService.getAllFiberColors();
    }

    @GetMapping({"/{id}"})
    public FiberColor getColorById(@PathVariable Integer id) throws ServiceException {
        return fiberColorService.getColorById(id);
    }

    @PostMapping("/create")
    public FiberColor addColor(@RequestBody FiberColor color) throws ServiceException {
        return fiberColorService.saveFiberColor(color);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteColor(@PathVariable(required = true) Integer id) throws ServiceException {
        fiberColorService.deleteFiberColor(id);
    }

    @PutMapping("/update/{id}")
    public FiberColor updateFiberColor(@PathVariable Integer id, @RequestBody FiberColor color) throws ServiceException {
        return fiberColorService.updateFiberColor(id, color);
    }
}
