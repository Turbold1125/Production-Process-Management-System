//package com.example.Backend.DTOs.Process;
//
//import com.example.Backend.Model.Fibers.FiberBlend;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Data
//public class FiberBlendResponse {
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
//    public FiberBlendResponse(FiberBlend fiberBlend) {
//        this.id = fiberBlend.getId();
//        this.orderId = fiberBlend.getOrder().getId();
//        this.fiberColorName = fiberBlend.getColor().getName();
//        this.baleNum = fiberBlend.getBaleNum();
//        this.roughWeight = fiberBlend.getRough_weight();
//        this.baleWeight = fiberBlend.getBale_weight();
//        this.conWeight = fiberBlend.getCon_weight();
//        this.moisture = fiberBlend.getMoisture();
//        this.dateTime = fiberBlend.getDateTime();
//    }
//}