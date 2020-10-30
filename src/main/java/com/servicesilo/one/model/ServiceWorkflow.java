package com.servicesilo.one.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ServiceWorkflow {

    private String uuid;
    private String workflowName;
    private String workflowDesc;
    private String tableId;

    private List<ServiceWorkflowNode> nodes;

}
