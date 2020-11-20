package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

/**
 * 表单对应的字段，根据具体情况可能需要扩展
 */
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
    private String colPlaceholder;
    private String colRules;
    private String colDict;
    private String colSort;

    private List<String> dictList;

}
