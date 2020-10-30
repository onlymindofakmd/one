package com.servicesilo.one.dict;

public enum ColType {
    VARCHAR("varchar", "字符串"),
    INT("int", "数字"),
    DATE("date", "日期"),
    DATETIME("datetime", "带时间日期"),
    LONGTEXT("longtext", "长字符");

    private String code;
    private String name;

    ColType (String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
