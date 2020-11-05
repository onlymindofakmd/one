package com.servicesilo.one.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ServiceTable {

    private String uuid;
    private String tableName;
    private String tableCode;
    private String tableType;

    private List<ServiceTableCol> cols;
}
