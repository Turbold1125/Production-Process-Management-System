package com.example.Backend.Api.User;

import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.User.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Tag(name = "User", description = "User api")
public interface UserApi {

    @Operation(summary = "Хэрэглэгчийн жагсаалт дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    List<User> getUsers() throws ServiceException;

    @Operation(summary = "Хэрэглэгч устгах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = ServiceException.class)) }),
            @ApiResponse(responseCode = "404") })
    void deleteUser(@PathVariable Long id) throws ServiceException;
}
