package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

/**
 * 流程表， 这里新增流程主要为了配置表单的时候选择对应流程
 */
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
