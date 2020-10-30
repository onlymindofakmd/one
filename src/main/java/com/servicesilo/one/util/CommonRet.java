package com.servicesilo.one.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonRet {
    private Integer status;
    private String msg;
    private Object data;
}
