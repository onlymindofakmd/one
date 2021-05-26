package com.servicesilo.one.service;

import com.servicesilo.one.dict.TrueOrFalse;
import com.servicesilo.one.model.Line;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RetUtil;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AddService implements IService {
    private static AddService me = new AddService();

    public static AddService getInstance() {
        return me;
    }

    @Override
    public CommonRet execute(Line line, ServiceUser user, Map<String, Object> params) {
        String preLine = params.get("preLine").toString();
        if (TrueOrFalse.TRUE.getCode().equals(preLine)) {
            
        } else {

        }
        return RetUtil.success();
    }
}
