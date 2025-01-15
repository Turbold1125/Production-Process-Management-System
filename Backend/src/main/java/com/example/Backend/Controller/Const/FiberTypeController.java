package com.example.Backend.Controller.Const;

import com.example.Backend.Api.Const.FiberTypeApi;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.FiberType;
import com.example.Backend.Service.Const.FiberTypeService;
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
    public FiberType getFiberTypeById(@PathVariable Long id) throws ServiceException {
        return fiberTypeService.getFiberTypeById(id);
    }

    @PostMapping("/create")
    public FiberType createFiberType(FiberType fiberType) throws ServiceException {
        return fiberTypeService.saveFiberType(fiberType);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFiberType(@PathVariable Long id) throws ServiceException {
        fiberTypeService.deleteFiberType(id);
    }

    @PutMapping("/update/{id}")
    public FiberType updateFiberType(@PathVariable Long id, @RequestBody FiberType fiberType) throws ServiceException {
        return fiberTypeService.updateFiberType(id, fiberType);
    }
}