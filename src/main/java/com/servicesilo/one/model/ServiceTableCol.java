package com.servicesilo.one.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTableCol {

    private String uuid;
    private String colName;
    private String colCode;
    private String colLength;
    private String colType;
    private String searchType;
    private Integer orderNum;
    private String tableId;

}
