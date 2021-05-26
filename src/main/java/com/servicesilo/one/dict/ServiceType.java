package com.servicesilo.one.dict;

import com.servicesilo.one.service.AddService;
import com.servicesilo.one.service.IService;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ServiceType {
    ADD_SERVICE("0001", "新增服务", AddService.getInstance());

    private String code;
    private String name;
    private IService service;
    ServiceType(String code, String name, IService service) {
        this.code = code;
        this.name = name;
        this.service = service;
    }

    public static IService findByCode (String code) {
        for (ServiceType serviceType: ServiceType.values()) {
            if (serviceType.getCode().equals(code)) {
                return serviceType.getService();
            }
        }
        return null;
    }

}
