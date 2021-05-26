package com.servicesilo.one.service;

import com.servicesilo.one.model.Line;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.util.CommonRet;

import java.util.Map;

public interface IService {

    public CommonRet execute (Line line, ServiceUser user, Map<String, Object> params);

}
