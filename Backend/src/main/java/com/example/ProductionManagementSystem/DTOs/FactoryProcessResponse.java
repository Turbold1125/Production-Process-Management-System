package com.example.ProductionManagementSystem.DTOs;

import lombok.Data;

@Data
public class FactoryProcessResponse {
    private Integer id;
    private String name;
    private String inputs;
    private String outputs;
    private String waste;

    public FactoryProcessResponse(Integer id, String name, String inputs, String outputs, String waste) {
        this.id = id;
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
        this.waste = waste;
    }
}
