package com.servicesilo.one.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceTableCol {

    private String uuid;
    private String colName;
    private String colCode;
    private String colLength;
    private String colType;
    private String searchType;
    private String orderNum;
    private String tableId;

}
