package com.servicesilo.one.dict;

public enum TrueOrFalse {
    TRUE("1", "是"),
    FALSE("2", "否");
    private String code;
    private String name;

    TrueOrFalse(String code, String name) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
