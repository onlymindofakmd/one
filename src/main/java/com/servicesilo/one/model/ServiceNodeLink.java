package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

/**
 * node-link表是数据流动起来的驱动表。每一个NodeLink对应一个操作。如展示 或者 保存。
 * 而且NodeLink会指向下一个节点Node，这样可以把数据推向下一个流程节点。
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceNodeLink {

    private String uuid;
    private String linkName;
    private String linkType;
    // 当前节点
    private String nodeId;
    // 目标节点
    private String linkTo;
    // 操作的tableId
    private String tableId;
    // 页面展示的col
    private String linkShowCols;
    // 操作的col
    private String linkOptCols;
    // 允许执行这个link的角色
    private String roleId;
    // 允许执行这个link的人员
    private String userId;

    public List<ServiceNodeLink> subLinks;

}
