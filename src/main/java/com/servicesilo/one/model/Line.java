package com.servicesilo.one.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Line {

    private String uuid;

    private String name;
    // 起始节点
    private String start;
    // 终止节点
    private String end;
    // 线路对应的服务
    private String service;
    // 传参变量
    private String reqCols;
    // 返回变量
    private String resCols;
    // 表单ID
    private String tableId;
    // 对应角色
    private String roles;

    private String parentId;

    private List<Line> subLines;
}
