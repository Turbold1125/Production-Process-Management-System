//package com.example.Backend.Api.Process;
//
//import com.example.Backend.DTOs.Process.FiberBlendRequest;
//import com.example.Backend.DTOs.Process.FiberBlendResponse;
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
//@Tag(name = "Түүхий эд холих", description = "Түүхий эд холих API")
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
//public interface FiberBlendApi {
//
//    @Operation(summary = "Холимог түүхий эдийн жагсаалт дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//                    @Content(schema = @Schema(implementation = FiberBlendResponse.class)) }),
//            @ApiResponse(responseCode = "204", description = "Агуулга байхгүй") })
//    List<FiberBlendResponse> getAllFiberBlends() throws ServiceException;
//
//    @Operation(summary = "Холимог түүхий эд дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    FiberBlendResponse getFiberBlendById(
//            @Parameter(required = true) @PathVariable Long id) throws ServiceException;
//
////    @Operation(summary = "Түүхий эд холих", security = {
////            @SecurityRequirement(name = "Authorization") })
////    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
////            @Content(schema = @Schema(implementation = FiberBlendResponse.class)) })
////    FiberBlendResponse processFiberBlend(
////            @Parameter(required = true) @RequestParam Long orderId, @RequestBody FiberBlendRequest dto) throws ServiceException;
//
//    @Operation(summary = "Холимог түүхий эд устгах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberBlendResponse.class)) })
//    void deleteFiberBlend(
//            @Parameter(required = true) @PathVariable Long id) throws ServiceException;
//
//    @Operation(summary = "Захиалга дахь холимог түүхий эдийн жагсаалт дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberBlendResponse.class)) })
//    List<FiberBlendResponse> getFiberBlendsByOrderId(
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