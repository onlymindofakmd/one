package com.servicesilo.one.dict;

public enum SearchType {
    EQUALS("1", "等于"),
    LIKE("2", ""),
    LEFTLIKE("3", ""),
    RIGHTLIKE("4", ""),
    LESS("5", ""),
    LESSEQ("6", ""),
    MORE("7", ""),
    MOREEQ("8", "")
    ;
    private String code;
    private String name;

    SearchType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
