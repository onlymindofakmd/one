package com.servicesilo.one.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ServiceUser {

    private String uuid;
    private String loginName;
    private String password;
    private String realName;
    private String groupId;
    private String roleId;

    private List<String> roles;
}
