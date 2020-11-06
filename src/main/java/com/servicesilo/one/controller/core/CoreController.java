package com.servicesilo.one.controller.core;

import com.servicesilo.one.model.ServiceNodeLink;
import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.service.CommonService;
import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RedisUtil;
import com.servicesilo.one.util.RetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * CoreController 这里就是整个项目的核心服务。这里提供的服务全部基于配置实现，
 * 理论上讲可以通过配置实现任何复杂的功能。
 * 项目初期计划是根据这样配置来动态实现各种服务，之后可以考虑通过代码生成、热部署等技术实现静态代码情况下的提供同样服务
 *
 * 注：没有实现的方法是还没有确定好的方法可能随时会删除掉，要根据以后研究判定是否需要对应的方法
 */
@Controller
@RequestMapping("/core")
public class CoreController {

    @Autowired
    private CommonService service;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 根据linkId 查询到保存操作对应的link，进而获得link中包含的linkOptCols， tableId等信息，然后保存。
     * @param params
     */
    public void save(@RequestBody Map<String, Object> params) {
        String linkId = params.get("link_id").toString();
        ServiceNodeLink link = redisUtil.getLink(linkId);
        ServiceTable table = redisUtil.getTable(link.getTableId());
        service.save(link, table, params);
    }

    /**
     * 同样获取对应link，然后获取tableId，linkShowCols等信息，完成页面展示。
     * @param linkId
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/show")
    public CommonRet show(@RequestParam String linkId) {
        ServiceNodeLink link = redisUtil.getLink(linkId);
        ServiceTable table = redisUtil.getTable(link.getTableId());
        return RetUtil.success(service.show(link, table));
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
     * 获取link 然后得到tableId和linkOptCols(这个是查询项目) 然后拼接sql查询
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/query")
    public CommonRet query(@RequestParam Map<String, Object> params) {
        String linkId = params.get("link_id").toString();
        String token = params.remove("access_token").toString();
        ServiceNodeLink link = redisUtil.getLink(linkId);
        ServiceTable table = redisUtil.getTable(link.getTableId());
        List<Map<String, Object>> results = service.list(link, table, params, redisUtil.getUser(token));
        return RetUtil.success(results);
    }

    /**
     * 用于工作流开启服务。暂时弃用
     */
    public void start(@RequestBody Map<String, Object> params) {

    }

    public void next() {

    }

}
