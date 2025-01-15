package com.example.ProductionManagementSystem.Controller.Const;

import com.example.ProductionManagementSystem.Api.Const.FiberTypeApi;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.FiberType;
import com.example.ProductionManagementSystem.Service.Const.FiberTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fiberType")
@RequiredArgsConstructor
public class FiberTypeController implements FiberTypeApi {
    private final FiberTypeService fiberTypeService;

    @GetMapping
    public List<FiberType> getAllFiberTypes() throws ServiceException {
        return fiberTypeService.getAllFiberTypes();
    }

    @GetMapping("/{id}")
    public FiberType getFiberTypeById(@PathVariable Integer id) throws ServiceException {
        return fiberTypeService.getFiberTypeById(id);
    }

    @PostMapping("/create")
    public FiberType createFiberType(FiberType fiberType) throws ServiceException {
        return fiberTypeService.saveFiberType(fiberType);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFiberType(@PathVariable Integer id) throws ServiceException {
        fiberTypeService.deleteFiberType(id);
    }

    @PutMapping("/update/{id}")
    public FiberType updateFiberType(@PathVariable Integer id, @RequestBody FiberType fiberType) throws ServiceException {
        return fiberTypeService.updateFiberType(id, fiberType);
    }
}