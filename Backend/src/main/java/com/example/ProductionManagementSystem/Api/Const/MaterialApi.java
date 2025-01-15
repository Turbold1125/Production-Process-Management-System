package com.example.ProductionManagementSystem.Api.Const;

import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.Material;
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

@Tag(name = "Material", description = "Material api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "404") })
public interface MaterialApi {
    @Operation(summary = "Материалын жагсаалт дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = Material.class)) })
    List<Material> getAllMaterial() throws ServiceException;

    @Operation(summary = "Материал дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = Material.class)) })
    Material getMaterialById(
            @Parameter(required = true) @PathVariable Integer id) throws ServiceException;

    @Operation(summary = "Материал нэмэх", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = Material.class)) })
    Material createMaterial(
            @Parameter(required = true, description = "Өнгө") @RequestBody Material material) throws ServiceException;

    @Operation(summary = "Материал устгах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = Material.class)) })
    void deleteMaterial(
            @Parameter(required = true) @PathVariable Integer id) throws ServiceException;

    @Operation(summary = "Материал засах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = Material.class)) })
    Material updateMaterial(
            @Parameter(required = true) @PathVariable Integer id, @RequestBody Material material) throws ServiceException;
}