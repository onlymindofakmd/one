package com.servicesilo.one.controller.core;

import com.servicesilo.one.model.Line;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RedisUtil;
import com.servicesilo.one.util.service.ServiceFactory;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

public class OnlyController {

    @Resource
    private RedisUtil redisUtil;

    public CommonRet execute (@RequestParam Map<String, Object> params) {
        String lineId = params.get("lineId").toString();
        String token = params.get("access_token").toString();
        ServiceUser user = redisUtil.getUser(token);
        Line line = redisUtil.getLink(lineId);
        return ServiceFactory.getInstance(line).execute(line, user, params);
    }

}
