package com.javaweb.app.enums;

public enum Province {
    LAO_CAI(1, "Lào Cai"),
    LAI_CHAU(2, "Lai Châu"),
    SON_LA(3, "Sơn La");

    private final int id;
    private final String name;

    Province(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
