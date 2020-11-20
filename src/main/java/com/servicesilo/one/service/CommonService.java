package com.servicesilo.one.service;

import com.servicesilo.one.datasource.CommonDAO;
import com.servicesilo.one.model.ServiceNodeLink;
import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.model.ServiceTableCol;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.util.RedisUtil;
import com.servicesilo.one.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    @Autowired
    private CommonDAO dao;

    public Map<String, Object> show(ServiceNodeLink link, ServiceTable table) {
        Map<String, Object> map = new HashMap<>();
        List<ServiceTableCol> ls = new ArrayList<>();
        for (ServiceTableCol col: table.getCols()) {
            if (link.getLinkShowCols().contains(col.getColCode())) {
                if (!link.getLinkOptCols().contains(col.getColCode())) {
                    col.setSearchType("999");
                }
                ls.add(col);
            }
        }
        map.put("fieldList", ls);
        map.put("formName", table.getTableName());
        map.put("links", link.getSubLinks());
        return map;
    }


    /**
     * 获取列表展示数据
     * @return 列表数据
     */
    public List<Map<String, Object>> list(ServiceNodeLink link,
                                          ServiceTable table,
                                          Map<String, Object> params,
                                          ServiceUser user) {
        String optCols = link.getLinkOptCols();

        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (String col: optCols.split(",")) {
            Object val = params.get(col);
            if (val != null && !StringUtils.isEmpty(val.toString())) {
                keys.add(col);
                values.add(val);
            }
        }

        String sql = TableUtil.makeSearchSql(link, table, keys, user);
        return dao.find(sql, values.toArray());
    }


    public Map<String, Object> findById(ServiceTable table, String uuid) {
        assert table != null;
        String sql = "select * from " + table.getTableCode() + " where uuid = ?";
        return dao.findOne(sql, uuid);
    }

    public Map<String, Object> findOneBySql(String sql, Object ...args) {
        return dao.findOne(sql, args);
    }

    public List<Map<String, Object>> findBySql(String sql, Object ...args) {
        return dao.find(sql, args);
    }

    public void save(ServiceNodeLink link, ServiceTable table, Map<String, Object> params) {
        String optCols = link.getLinkOptCols();
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (String col: optCols.split(",")) {
            Object val = params.get(col);
            if (val != null && !StringUtils.isEmpty(val.toString())) {
                keys.add(col);
                values.add(val);
            }
        }
        String sql = TableUtil.makeAddSql(table, keys);
        dao.addOrUpdate(sql, values.toArray());
    }
}
