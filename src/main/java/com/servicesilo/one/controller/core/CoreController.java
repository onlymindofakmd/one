package com.servicesilo.one.controller.core;

import com.servicesilo.one.service.CommonService;
import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/core")
public class CoreController {

    @Autowired
    private CommonService service;

    public void save(@RequestBody Map<String, Object> params) {
        String tableId = params.get("tableId").toString();
        service.save(tableId, params);
    }

    public void update() {

    }

    public void delete() {

    }

    /**
     * 用于普通的保存服务
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/query")
    public CommonRet query(@RequestBody Map<String, Object> params) {
        String tableId = params.get("tableId").toString();
        List<Map<String, Object>> results = service.list(tableId, params);
        return RetUtil.success(results);
    }

    /**
     * 用于工作流开启服务。
     */
    public void start(@RequestBody Map<String, Object> params) {

    }

    public void next() {

    }

    public void prev() {

    }

    public void stop() {
        
    }
}
