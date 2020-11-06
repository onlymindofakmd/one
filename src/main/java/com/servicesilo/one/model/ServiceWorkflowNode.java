package com.servicesilo.one.model;

import lombok.*;

/**
 * 流程节点。
 */
@Getter
@Setter
@Builder
@NoArgsConstructor // 不添加下面两个注解会报错
@AllArgsConstructor
public class ServiceWorkflowNode {

    private String uuid;
    private String nodeName;
    // 目前这个字段没用上。都是在link中控制。
    private String nodeType;
    private String nodeDesc;
    private String workflowId;

}
