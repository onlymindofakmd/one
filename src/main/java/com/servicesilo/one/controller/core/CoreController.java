package com.servicesilo.one.controller.core;

import com.servicesilo.one.model.ServiceNodeLink;
import com.servicesilo.one.service.CommonService;
import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RedisUtil;
import com.servicesilo.one.util.RetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/core")
public class CoreController {

    @Autowired
    private CommonService service;

    /**
     * 保存服务
     * @param params
     */
    public void save(@RequestBody Map<String, Object> params) {
        String linkId = params.get("link_id").toString();
        service.save(linkId, params);
    }

    /**
     * 更新服务
     */
    public void update() {

    }

    public void delete() {

    }

    /**
     * 查询服务
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/query")
    public CommonRet query(@RequestParam Map<String, Object> params) {
        String linkId = params.get("link_id").toString();
        String token = params.remove("access_token").toString();
        List<Map<String, Object>> results = service.list(linkId, params, RedisUtil.getUser(token));
        return RetUtil.success(results);
    }

    /**
     * 用于工作流开启服务。
     */
    public void start(@RequestBody Map<String, Object> params) {

    }

    public void next() {

    }

}
