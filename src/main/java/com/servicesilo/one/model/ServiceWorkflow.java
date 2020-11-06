package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceWorkflow {

    private String uuid;
    private String workflowName;
    private String workflowDesc;

    private List<ServiceWorkflowNode> nodes;

}
