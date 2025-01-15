//package com.example.Backend.DTOs.Process;
//
//import com.example.Backend.Model.Fibers.Fiber;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Data
//public class FiberReceiveResponse {
//    private Long id;
//    private Long orderId;
//
//    private String fiberColorName;
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
//    public FiberReceiveResponse(Fiber fiber) {
//        this.id = fiber.getId();
//        this.orderId = fiber.getOrder().getId();
//        this.fiberColorName = fiber.getColor().getName();
//        this.dateTime = fiber.getDateTime();
//        this.baleNum = fiber.getBaleNum();
//        this.roughWeight = fiber.getRough_weight();
//        this.baleWeight = fiber.getBale_weight();
//        this.conWeight = fiber.getCon_weight();
//        this.moisture = fiber.getMoisture();
//    }
//}