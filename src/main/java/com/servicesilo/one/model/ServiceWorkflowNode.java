package com.servicesilo.one.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor // 不添加下面两个注解会报错
@AllArgsConstructor
public class ServiceWorkflowNode {

    private String uuid;
    private String nodeName;
    private String nodeType;
    private String nodeDesc;
    private String workflowId;

}
