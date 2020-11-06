package com.servicesilo.one.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUser {

    private String uuid;
    private String loginName;
    private String password;
    private String realName;
    private String groupId;
    private String roleId;

    private List<String> roles;
}
