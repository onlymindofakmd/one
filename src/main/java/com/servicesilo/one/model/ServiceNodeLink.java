package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceNodeLink {

    private String uuid;
    private String linkName;
    private String linkType;
    private String nodeId;
    private String linkTo;
    private String tableId;
    private String linkShowCols;
    private String linkOptCols;
    private String roleId;
    private String userId;

    public List<ServiceNodeLink> subLinks;

}
