package com.servicesilo.one.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceWorkflowNode {

    private String uuid;
    private String nodeName;
    private String nodeType;
    private String nodeDesc;
    private String workflowId;

}
