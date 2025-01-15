package com.example.Backend.Api.Const;

import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.User.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Tag(name = "Customer", description = "Customer api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "401", content = {
                @Content(schema = @Schema(implementation = ServiceException.class)) }),
        @ApiResponse(responseCode = "404") })
public interface CustomerApi {

    @Operation(summary = "Харилцагчийн жагсаалт дуудах", security = {
            @SecurityRequirement(name = "Authorization") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "204", description = "No Content") })
    List<Customer> getAllCustomers() throws ServiceException;

    @Operation(summary = "Харилцагч нэмэх", security = {
            @SecurityRequirement(name = "Authorization") })
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Customer.class)) })
    Customer createCustomer(
            @Parameter(required = true, description = "Харилцагчийн мэдээлэл") @RequestBody Customer customer) throws ServiceException;

    @Operation(summary = "Харилцагчийн мэдээлэл шинэчлэх", security = {
            @SecurityRequirement(name = "Authorization") })
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Customer.class)) })
    Customer updateCustomer(
            @Parameter(required = true) @PathVariable Long id, @RequestBody Customer customer) throws ServiceException;

    @Operation(summary = "Харилцагч устгах", security = {
            @SecurityRequirement(name = "Authorization") })
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(schema = @Schema(implementation = Customer.class)) })
    void deleteCustomer(
            @Parameter(required = true) @PathVariable Long id) throws ServiceException;
}
