package com.servicesilo.one.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceTableOpt {

    private String uuid;
    private String optName;
    private String optCols;
    private String nodeId;
    private String tableId;
    private String roleId;
    private String userId;

}
