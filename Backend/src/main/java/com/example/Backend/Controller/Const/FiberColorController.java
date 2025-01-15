package com.example.Backend.Controller.Const;

import com.example.Backend.Api.Const.FiberColorApi;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Service.Const.FiberColorService;
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
    public FiberColor getColorById(@PathVariable Long id) throws ServiceException {
        return fiberColorService.getColorById(id);
    }

    @PostMapping("/create")
    public FiberColor addColor(@RequestBody FiberColor color) throws ServiceException {
        return fiberColorService.saveFiberColor(color);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteColor(@PathVariable(required = true) Long id) throws ServiceException {
        fiberColorService.deleteFiberColor(id);
    }

    @PutMapping("/update/{id}")
    public FiberColor updateFiberColor(@PathVariable Long id, @RequestBody FiberColor color) throws ServiceException {
        return fiberColorService.updateFiberColor(id, color);
    }
}
