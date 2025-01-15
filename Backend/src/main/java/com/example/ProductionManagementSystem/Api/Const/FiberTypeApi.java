package com.example.ProductionManagementSystem.Api.Const;

import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.FiberType;
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

@Tag(name = "FiberType", description = "FiberType api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "404") })
public interface FiberTypeApi {

    @Operation(summary = "Түүхий эдийн жагсаалт дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FiberType.class)) })
    List<FiberType> getAllFiberTypes() throws ServiceException;

    @Operation(summary = "Түүхий эд дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FiberType.class)) })
    FiberType getFiberTypeById(
            @Parameter(required = true) @PathVariable Integer id) throws ServiceException;

    @Operation(summary = "Түүхий эд нэмэх", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FiberType.class)) })
    FiberType createFiberType(
            @Parameter(required = true, description = "Өнгө") @RequestBody FiberType type) throws ServiceException;

    @Operation(summary = "Түүхий эд устгах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FiberType.class)) })
    void deleteFiberType(
            @Parameter(required = true) @PathVariable Integer id) throws ServiceException;

    @Operation(summary = "Түүхий эд засах", security = {
            @SecurityRequirement(name = "Authorization") })
     @ApiResponse(responseCode = "200", description = "Success", content = {
             @Content(schema = @Schema(implementation = FiberType.class)) })
    FiberType updateFiberType(
            @Parameter(required = true) @PathVariable Integer id, @RequestBody FiberType fiberType) throws ServiceException;
}
