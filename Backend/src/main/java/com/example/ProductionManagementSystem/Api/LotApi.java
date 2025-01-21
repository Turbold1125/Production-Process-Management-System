package com.example.ProductionManagementSystem.Api;

import com.example.ProductionManagementSystem.DTOs.LotRequest;
import com.example.ProductionManagementSystem.Model.Lot;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Lot", description = "Лотын API")
public interface LotApi {

    List<Lot> getAllLots();

    Lot createLot(@RequestBody LotRequest lotRequest);

    List<Lot> getLotsByOrderId(@RequestParam Integer orderId);
}
