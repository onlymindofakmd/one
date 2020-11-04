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
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommonService {

    @Autowired
    private CommonDAO dao;

    /**
     * 获取列表展示数据
     * @return 列表数据
     */
    public List<Map<String, Object>> list(String linkId,
                                          Map<String, Object> params,
                                          ServiceUser user) {
        // 这里需要确保这些key对应的值都不为空。
        ServiceNodeLink link = RedisUtil.getLink(linkId);
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

        String sql = TableUtil.makeSearchSql(link, keys, user);
        return dao.find(sql, values.toArray());
    }


    public Map<String, Object> findById(String tableId, String uuid) {
        ServiceTable table = TableUtil.getTable(tableId);
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

    public void save(String linkId, Map<String, Object> params) {
        ServiceNodeLink link = RedisUtil.getLink(linkId);
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
        String sql = TableUtil.makeAddSql(linkId, keys);
        dao.addOrUpdate(sql, values.toArray());
    }
}
