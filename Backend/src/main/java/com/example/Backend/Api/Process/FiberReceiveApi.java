//package com.example.Backend.Api.Process;
//
//import com.example.Backend.DTOs.Process.FiberReceiveRequest;
//import com.example.Backend.DTOs.Process.FiberReceiveResponse;
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
//
//import java.util.List;
//
//@Tag(name = "Түүхий эд хүлээн авах", description = "Түүхий эд хүлээн авах API")
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
//public interface FiberReceiveApi {
//
//    @Operation(summary = "Хүлээн авсан түүхий эдийн жагсаалт дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//                    @Content(schema = @Schema(implementation = FiberReceiveResponse.class)) }),
//            @ApiResponse(responseCode = "204", description = "Агуулга байхгүй") })
//    List<FiberReceiveResponse> getAllFiberReceives() throws ServiceException;
//
//    @Operation(summary = "Хүлээн авсан түүхий эд дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    FiberReceiveResponse getFiberReceiveById(
//            @Parameter(required = true) @PathVariable Long id) throws ServiceException;
//
//    @Operation(summary = "Түүхий эд хүлээн авах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberReceiveResponse.class)) })
//    FiberReceiveResponse createFiberReceive(
//            @Parameter(required = true) @RequestBody FiberReceiveRequest dto) throws ServiceException;
//
//    @Operation(summary = "Хүлээн авсан түүхий эд устгах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberReceiveResponse.class)) })
//    void deleteFiberReceive(
//            @Parameter(required = true) @PathVariable Long id) throws ServiceException;
//
//    @Operation(summary = "Захиалг дахь хүлээн авсан түүхий эдийн жагсаалт дуудах", security = {
//            @SecurityRequirement(name = "Authorization") })
//    @ApiResponse(responseCode = "200", description = "Амжилттай", content = {
//            @Content(schema = @Schema(implementation = FiberReceiveResponse.class)) })
//    List<FiberReceiveResponse> getFiberReceivesByOrderId(
//            @Parameter(required = true) @PathVariable Long orderId) throws ServiceException;
//}