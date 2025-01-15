package com.example.ProductionManagementSystem.Api;

import com.example.ProductionManagementSystem.Constants.ProcessStatus;
import com.example.ProductionManagementSystem.DTOs.EndProcessRequest;
import com.example.ProductionManagementSystem.DTOs.ProcessRequest;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Inventory;
import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Model.ProcessLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Process", description = "Процессийн API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class))}),
        @ApiResponse(responseCode = "404") })
public interface ProcessApi {

    @Operation(summary = "Процесс эхлүүлэх", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
                    @Content(schema = @Schema(implementation = Process.class))}),
            @ApiResponse(responseCode = "204", description = "Агуулга байхгүй") })
    Process startProcess(@RequestBody ProcessRequest request) throws ServiceException;

    @Operation(summary = "Процесс дуусгах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
            @Content(schema = @Schema(implementation = Inventory.class))})
    Inventory endProcess(
            @Parameter(required = true) @RequestBody EndProcessRequest request) throws ServiceException;

    @Operation(summary = "Бүх процессийн бүртгэлийг авах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
            @Content(schema = @Schema(implementation = ProcessLog.class))})
    List<ProcessLog> getAllProcessLogs() throws ServiceException;

    @Operation(summary = "Захиалгын процессийн бүртгэлийг авах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
            @Content(schema = @Schema(implementation = ProcessLog.class))})
    List<ProcessLog> getProcessLogsForOrder(Integer orderId);

    @Operation(summary = "Active process лавлах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
            @Content(schema = @Schema(implementation = Process.class))})
    List<Process> getActiveProcessLogs();

    @Operation(summary = "Хэрэглэгчийн процесс лавлах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
            @Content(schema = @Schema(implementation = Process.class))})
    List<Process> getProcessLogsForCustomer(@PathVariable String customerName);

    @Operation(summary = "Хэрэглэгчийн процесс status-аар лавлах", security = {
            @SecurityRequirement(name = "Authorization")})
    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
            @Content(schema = @Schema(implementation = Process.class))})
    public List<Process> getProcessesByStatus(@PathVariable ProcessStatus status);
}
