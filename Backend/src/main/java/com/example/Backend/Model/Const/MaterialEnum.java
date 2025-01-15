package com.example.Backend.Model.Const;

public enum MaterialEnum {
    RAW_MATERIAL("Түүхий эд"),
    BLENDEN_MATERIAL("Хольсон түүхий эд"),
    ROVEN("Цувимал"),
    SINGLE_YARN("Дан утас"),
    WINDED_YARN("Ороосон утас"),
    DOUBLED_YARN("Давхарласан утас"),
    TWISTED_YARN("Эрчилсэн утас");

    private final String name;

    MaterialEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
