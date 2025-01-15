package com.example.Backend.Api;

import com.example.Backend.DTOs.OrderRequest;
import com.example.Backend.DTOs.OrderResponse;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Order;
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
public interface OrderApi {

    @Operation(summary = "Retrieve all orders", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Order.class)) }),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    List<Order> getAllOrders() throws ServiceException;

    @Operation(summary = "Retrieve order by ID", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Order.class)) }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    Order getOrderById(
            @Parameter(required = true) @PathVariable Long orderId) throws ServiceException;

    @Operation(summary = "Create a new order", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created", content = {
                    @Content(schema = @Schema(implementation = OrderResponse.class)) }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }) })
    Order createOrder(
            @Parameter(required = true, description = "Order") @RequestBody OrderRequest orderDTO) throws ServiceException;

    @Operation(summary = "Delete an order by ID", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted"),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    void deleteOrder(
            @Parameter(required = true) @PathVariable Long orderId) throws ServiceException;

//    @Operation(summary = "Retrieve recent orders", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success", content = {
//                    @Content(schema = @Schema(implementation = OrderResponse.class)) }),
//            @ApiResponse(responseCode = "204", description = "No Content"),
//            @ApiResponse(responseCode = "400", content = {
//                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
//            @ApiResponse(responseCode = "401", content = {
//                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
//            @ApiResponse(responseCode = "404") })
//    List<OrderResponse> getRecentOrders() throws ServiceException;
}

//
//import com.example.Backend.Exception.ServiceException;
//import com.example.Backend.Model.Order;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Tag(name = "Order", description = "Order api")
//public interface OrderApi {
//    @Operation(summary = "Create a new order", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Created", content = {
//                    @Content(schema = @Schema(implementation = Order.class)) }),
//            @ApiResponse(responseCode = "400", content = {
//                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
//            @ApiResponse(responseCode = "401", content = {
//                    @Content(schema = @Schema(implementation = ServiceException.class)) }) })
//    Order createOrder(
//            @Parameter(required = true, description = "Order") @RequestBody Order order,
//            @RequestParam List<Long> selectedProcessIds) throws ServiceException;
////
//}
