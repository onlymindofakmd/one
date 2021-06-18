package com.servicesilo.one.service;

import com.servicesilo.one.model.Line;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.util.CommonRet;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeleteService implements IService {
    @Override
    public CommonRet execute(Line line, ServiceUser user, Map<String, Object> params) {
        

        return null;
    }
}
