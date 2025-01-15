//package com.example.Backend.DTOs.Process;
//
//import com.example.Backend.Model.Fibers.FiberDye;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Data
//public class FiberDyeResponse {
//    private Long id;
//    private Long orderId;
//
//    private Long fiberColorId;
//    private String fiberOutColorName;
//
//    private int baleNum;
//
//    private double roughWeight;
//    private double baleWeight;
//    private double conWeight;
//    private double moisture;
//
//    private LocalDateTime dateTime;
//
//    public FiberDyeResponse(FiberDye fiber) {
//        this.id = fiber.getId();
//        this.orderId = fiber.getOrder().getId();
//        this.fiberColorId = fiber.getColor().getId();
//        this.fiberOutColorName = fiber.getOut_color().getName();
//        this.roughWeight = fiber.getRough_weight();
//        this.baleWeight = fiber.getBale_weight();
//        this.conWeight = fiber.getCon_weight();
//        this.moisture = fiber.getMoisture();
//        this.baleNum = fiber.getBaleNum();
//        this.dateTime = fiber.getDateTime();
//    }
//}