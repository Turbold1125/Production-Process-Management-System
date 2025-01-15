package com.example.ProductionManagementSystem.Model.Const;

public enum MatEnum {
    FIBER("Түүхий эд", "Fiber"),
    FIBERBLENDED("Хольсон түүхий эд", "Fiber Blended"),
    ROVEN("Цувимал", "Roven"),
    SINGLE_YARN("Дан утас", "Single Yarn"),
    WINDED_YARN("Ороосон утас", "Winded Yarn"),
    DOUBLED_YARN("Давхарласан утас", "Doubled Yarn"),
    TWISTED_YARN("Эрчилсэн утас", "Twisted Yarn");

    private final String name;
    private final String nameEn;

    MatEnum(String name, String nameEn) {
        this.name = name;
        this.nameEn = nameEn;
    }

    public String getName() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public static MatEnum fromName(String name) {
        for (MatEnum matEnum : values()) {
            if (matEnum.name.equals(name)) {
                return matEnum;
            }
        }
        throw new IllegalArgumentException("Unsupported fiber material: " + name);
    }
}