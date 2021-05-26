package com.servicesilo.one.util.service;

import com.servicesilo.one.dict.ServiceType;
import com.servicesilo.one.model.Line;
import com.servicesilo.one.service.IService;

public class ServiceFactory {

    public static IService getInstance(Line line) {

        return ServiceType.findByCode(line.getService());
    }

}
