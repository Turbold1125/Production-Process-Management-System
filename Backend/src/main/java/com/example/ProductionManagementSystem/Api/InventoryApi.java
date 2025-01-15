package com.example.ProductionManagementSystem.Api;

import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Inventory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Inventory", description = "Агуулахын API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
                @Content(schema = @Schema(implementation = Inventory.class))}),
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "404") })
public interface InventoryApi {
    @Operation(summary = "Бүх агуулахын мэдээллийг авах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
                    @Content(schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "204", description = "Агуулга байхгүй") })
    List<Inventory> getAllInventory() throws ServiceException;

    @Operation(summary = "Хүлээн авах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Үүсгэсэн", content = {
            @Content(schema = @Schema(implementation = Inventory.class)) })
    Inventory createInventory(
            @Parameter(required = true, description = "Агуулахын мэдээлэл") @RequestBody Inventory inventory) throws ServiceException;

    @Operation(summary = "Төрөл бүрийн талбараар агуулахын зүйлсийг хайх", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = @Content(schema = @Schema(implementation = Inventory.class)))
    @GetMapping("/search")
    List<Inventory> searchItems(
            @Parameter(description = "Харилцагчийн нэр") @RequestParam(required = false) String customerName,
            @Parameter(description = "Шилэн материал") @RequestParam(required = false) String fiberMaterial
    ) throws ServiceException;

    @Operation(summary = "Процессийн төрлөөр агуулахыг шүүх", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = @Content(schema = @Schema(implementation = Inventory.class)))
    @GetMapping("/process/{processType}")
    List<Inventory> getFilteredInventory(
            @Parameter(required = true) @PathVariable String customerName, @PathVariable String processType) throws ServiceException;
}