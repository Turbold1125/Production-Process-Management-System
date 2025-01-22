package com.example.ProductionManagementSystem.Api;

import com.example.ProductionManagementSystem.DTOs.Order.OrderResponse;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Order;
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

@Tag(name = "Order", description = "Order api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "404") })
public interface OrderApi {

    @Operation(summary = "Бүх захиалгын мэдээлэл лавлах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "204", description = "No Content")})
    List<OrderResponse> getAllOrders() throws ServiceException;

    @Operation(summary = "Захиалгын мэдээлэл лавлах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = Order.class))})
    OrderResponse getOrderById(
            @Parameter(required = true) @PathVariable Integer orderId) throws ServiceException;

    @Operation(summary = "Захиалга үүсгэх", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Created", content = {
            @Content(schema = @Schema(implementation = Order.class))})
    OrderResponse createOrder(
            @Parameter(required = true, description = "Order") @RequestBody Order order) throws ServiceException;

    @Operation(summary = "Захиалга устгах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Deleted")
    void deleteOrder(
            @Parameter(required = true) @PathVariable Integer orderId) throws ServiceException;

    @Operation(summary = "Сүүлийн 5 захиалга лавлах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Created", content = {
            @Content(schema = @Schema(implementation = Order.class))})
    List<OrderResponse> getRecentOrders();
}