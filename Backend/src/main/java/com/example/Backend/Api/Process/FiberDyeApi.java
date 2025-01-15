//package com.example.Backend.Api.Process;
//
//import com.example.Backend.DTOs.Process.FiberDyeRequest;
//import com.example.Backend.DTOs.Process.FiberDyeResponse;
//import com.example.Backend.Exception.ServiceException;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Tag(name = "Түүхий эд будах", description = "Түүхий эд будах API")
//@ApiResponses(value = {
//        @ApiResponse(responseCode = "400", content = {
//                @Content(schema = @Schema(implementation = ServiceException.class))
//        }),
//        @ApiResponse(responseCode = "401", content = {
//                @Content(schema = @Schema(implementation = ServiceException.class))
//        }),
//        @ApiResponse(responseCode = "404", content = {
//                @Content(schema = @Schema(implementation = ServiceException.class))
//        })
//})
//public interface FiberDyeApi {
//
//    @Operation(summary = "Будсан түүхий эдийн жагсаалт дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//                    @Content(schema = @Schema(implementation = FiberDyeResponse.class)) }),
//            @ApiResponse(responseCode = "204", description = "Агуулга байхгүй") })
//    List<FiberDyeResponse> getAllFiberDyes() throws ServiceException;
//
//    @Operation(summary = "Будсан түүхий эд дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    FiberDyeResponse getFiberReceiveById(
//            @Parameter(required = true) @PathVariable Long id) throws ServiceException;
//
//    @Operation(summary = "Түүхий эд будах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberDyeResponse.class)) })
//    FiberDyeResponse dyeFiber(
//            @Parameter(required = true)  @RequestBody FiberDyeRequest dto) throws ServiceException;
//
//    @Operation(summary = "Будсан түүхий эд устгах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberDyeResponse.class)) })
//    void deleteFiberReceive(
//            @Parameter(required = true) @PathVariable Long id) throws ServiceException;
//
//    @Operation(summary = "Захиалга дахь будсан түүхий эдийн жагсаалт дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberDyeResponse.class)) })
//    List<FiberDyeResponse> getFiberDyesByOrderId(
//            @Parameter(required = true) @PathVariable Long orderId) throws ServiceException;
//
//    @Operation(summary = "Түүхий эд хүлээн авсан жин", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = Double.class)) })
//    double getProcessedWeightInFiberReceive(
//            @Parameter(required = true) @PathVariable Long orderId) throws ServiceException;
//
//    @Operation(summary = "Будсан түүхий эдийн жин", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = Double.class)) })
//    double getProcessedWeightInFiberDye(
//            @Parameter(required = true) @PathVariable Long orderId) throws ServiceException;
//}