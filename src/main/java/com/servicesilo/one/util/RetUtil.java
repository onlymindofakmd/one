package com.servicesilo.one.util;

public class RetUtil {

    public static CommonRet success () {
        return CommonRet.builder().status(200).msg("操作成功！").build();
    }

    public static CommonRet success (String msg) {
        return CommonRet.builder().status(200).msg(msg).build();
    }

    public static CommonRet success (Object data) {
        return CommonRet.builder().status(200).msg("操作成功！").data(data).build();
    }

    public static CommonRet success (String msg, Object data) {
        return CommonRet.builder().status(200).msg(msg).data(data).build();
    }

    public static CommonRet fail (String msg) {
        return CommonRet.builder().status(200).msg(msg).build();
    }

    public static CommonRet fail () {
        return CommonRet.builder().status(200).msg("操作失败！").build();
    }
}
