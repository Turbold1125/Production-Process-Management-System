package com.example.Backend.Api.Const;

import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.Const.FiberColor;
import com.example.Backend.Model.Const.FiberType;
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

@Tag(name = "FiberColor", description = "FiberColor api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "404") })
public interface FiberColorApi {

    @Operation(summary = "Өнгөний жагсаалт дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = FiberColor.class)) }),
            @ApiResponse(responseCode = "204", description = "No Content") })
    List<FiberColor> getAllColors() throws ServiceException;

    @Operation(summary = "Өнгө дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FiberColor.class)) })
    FiberColor getColorById(
            @Parameter(required = true) @PathVariable Long id) throws ServiceException;

    @Operation(summary = "Өнгө нэмэх", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FiberColor.class)) })
    FiberColor addColor(
            @Parameter(required = true, description = "Өнгө") @RequestBody FiberColor color) throws ServiceException;

    @Operation(summary = "Өнгө устгах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = FiberColor.class)) })
    void deleteColor(
            @Parameter(required = true) @PathVariable Long id) throws ServiceException;

    @Operation(summary = "Өнгө засах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FiberColor.class)) })
    FiberColor updateFiberColor(
            @Parameter(required = true) @PathVariable Long id, @RequestBody FiberColor FiberColor) throws ServiceException;
}
