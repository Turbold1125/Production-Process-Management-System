package com.example.Backend.Api;

import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Inventory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Inventory", description = "Inventory api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(schema = @Schema(implementation = Inventory.class))}),
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "404") })
public interface InventoryApi {

    @Operation(summary = "Retrieve all Inventory", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Inventory.class))}),
            @ApiResponse(responseCode = "204", description = "No Content") })
    List<Inventory> getAllItems() throws ServiceException;

    @Operation(summary = "Retrieve Inventory by ID", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = Inventory.class))})
    Inventory getItemById(
            @Parameter(required = true) @PathVariable Long inventoryId) throws ServiceException;

    @Operation(summary = "Create a new order", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Created", content = {
            @Content(schema = @Schema(implementation = Inventory.class)) })
    Inventory createItem(
            @Parameter(required = true, description = "Inventory") @RequestBody Inventory inventory) throws ServiceException;

    @Operation(summary = "Delete an order by ID", security = {
            @SecurityRequirement(name = "Authorization")})
            @ApiResponse(responseCode = "200", description = "Deleted")
    void deleteItem(
            @Parameter(required = true) @PathVariable Long inventoryId) throws ServiceException;

//    @Operation(summary = "Find items by customer, material, color, and fiber type", security = {
//            @SecurityRequirement(name = "Authorization")})
//    @ApiResponse(responseCode = "200", description = "Success", content = {
//            @Content(schema = @Schema(implementation = Inventory.class))})
//    List<Inventory> findByCustomerAndMaterialAndColorAndFiberTypes(
//            @Parameter(required = true) @RequestParam String customer,
//            @Parameter(required = true) @RequestParam String material,
//            @Parameter(required = true) @RequestParam String color,
//            @Parameter(required = true) @RequestParam String fiberType) throws ServiceException;
}