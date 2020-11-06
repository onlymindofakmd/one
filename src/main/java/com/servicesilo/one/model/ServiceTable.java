package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

/**
 * 这里配置业务需要的表单。
 * 待扩展
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTable {

    private String uuid;
    private String tableName;
    private String tableCode;
    // 扩展字段， 目前没用
    private String tableType;

    private List<ServiceTableCol> cols;
}
