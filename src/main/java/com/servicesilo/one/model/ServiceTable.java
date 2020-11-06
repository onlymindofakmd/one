package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTable {

    private String uuid;
    private String tableName;
    private String tableCode;
    private String tableType;

    private List<ServiceTableCol> cols;
}
