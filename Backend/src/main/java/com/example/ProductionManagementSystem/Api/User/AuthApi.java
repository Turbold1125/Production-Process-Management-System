package com.example.ProductionManagementSystem.Api.User;

import com.example.ProductionManagementSystem.DTOs.User.AuthRequest;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.DTOs.User.AuthResponse;
import com.example.ProductionManagementSystem.DTOs.User.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Authenticate", description = "Authenticate api")
public interface AuthApi {

    @Operation(summary = "Register a new user", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = AuthResponse.class)) }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    AuthResponse register(@RequestBody RegisterRequest request) throws ServiceException;

    @Operation(summary = "Login a user", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = AuthResponse.class)) }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    AuthResponse login(@RequestBody AuthRequest request) throws ServiceException;

    @Operation(summary = "Delete a user", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    void delete(@RequestParam(required = true) Integer id) throws ServiceException;
}
