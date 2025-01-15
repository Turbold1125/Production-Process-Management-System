package com.example.Backend.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long customer;
    private Long color;
    private Long type;
    private double weight;
    private List<Long> processes;
}