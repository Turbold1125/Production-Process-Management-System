package com.example.ProductionManagementSystem.Controller;

import com.example.ProductionManagementSystem.Api.LotApi;
import com.example.ProductionManagementSystem.DTOs.LotRequest;
import com.example.ProductionManagementSystem.Model.Lot;
import com.example.ProductionManagementSystem.Service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
public class LotController implements LotApi {

    private final LotService lotService;

    @GetMapping("/all")
    public List<Lot> getAllLots() {
        return lotService.getAllLots();
    }

    @PostMapping("/create")
    public Lot createLot(@RequestBody LotRequest lotRequest) {
        return lotService.createLot(lotRequest);
    }

    @GetMapping("/byOrderId")
    public List<Lot> getLotsByOrderId(@RequestParam Integer orderId){
        return lotService.getLotsByOrderId(orderId);
    }

    @GetMapping("/withBatches")
    public List<Lot> getLotsWithBatches(@RequestParam Integer orderId) {
        return lotService.getLotsWithBatches(orderId);
    }
}
