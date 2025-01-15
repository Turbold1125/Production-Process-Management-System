package com.example.Backend.Controller.Const;

import com.example.Backend.Api.Const.FactoryProcessApi;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.FactoryProcess;
import com.example.Backend.Service.Const.FactoryProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factoryProcess")
@RequiredArgsConstructor
public class FactoryProcessController implements FactoryProcessApi {

    private final FactoryProcessService factoryProcessService;

    @GetMapping
    public List<FactoryProcess> getAllProcess() throws ServiceException {
        return factoryProcessService.getAllProcess();
    }

    @GetMapping("/{id}")
    public FactoryProcess getProcessById(@PathVariable Long id) throws ServiceException {
        return factoryProcessService.getProcessById(id);
    }

    @PostMapping("/create")
    public FactoryProcess saveProcess(@RequestBody FactoryProcess factoryProcess) throws ServiceException {
        return factoryProcessService.saveProcess(factoryProcess);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProcess(@PathVariable(required = true) Long id) throws ServiceException {
        factoryProcessService.deleteProcess(id);
    }

    @PutMapping("/update/{id}")
    public FactoryProcess updateProcess(@PathVariable Long id, @RequestBody FactoryProcess factoryProcess) throws ServiceException {
        return factoryProcessService.updateProcess(id, factoryProcess);
    }
}
