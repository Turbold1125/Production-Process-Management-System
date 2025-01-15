package com.example.ProductionManagementSystem.Api.Const;

import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Model.Const.FactoryProcess;
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

@Tag(name = "FactoryProcess", description = "FactoryProcess api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "404") })
public interface FactoryProcessApi {

    @Operation(summary = "Процессийн жагсаалт дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FactoryProcess.class)) })
    List<FactoryProcess> getAllProcess() throws ServiceException;

    @Operation(summary = "Процессийг дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FactoryProcess.class)) })
    FactoryProcess getProcessById(
            @Parameter(required = true) @PathVariable Integer id) throws ServiceException;

    @Operation(summary = "Процесс нэмэх", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FactoryProcess.class)) })
    FactoryProcess saveProcess(
            @Parameter(required = true, description = "Процесс") @RequestBody FactoryProcess factoryProcess) throws ServiceException;

    @Operation(summary = "Процесс устгах", security = {
            @SecurityRequirement(name = "Authorization") })
            @ApiResponse(responseCode = "204", description = "No Content")
     void deleteProcess(
                @Parameter(required = true) @PathVariable Integer id) throws ServiceException;

    @Operation(summary = "Процесс засах", security = {
            @SecurityRequirement(name = "Authorization") })
   @ApiResponse(responseCode = "200", description = "Success", content = {
            @Content(schema = @Schema(implementation = FactoryProcess.class)) })
    FactoryProcess updateProcess(
            @Parameter(required = true) @PathVariable Integer id, @RequestBody FactoryProcess FactoryProcess) throws ServiceException;

}
