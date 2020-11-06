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
     * 用于工作流开启服务。
     */
    public void start(@RequestBody Map<String, Object> params) {

    }

    public void next() {

    }

}
