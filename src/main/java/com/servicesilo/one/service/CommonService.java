package com.servicesilo.one.service;

import com.servicesilo.one.datasource.CommonDAO;
import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.model.ServiceTableCol;
import com.servicesilo.one.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Map<String, Object>> list(String tableId, Map<String, Object> params) {
        // 这里需要确保这些key对应的值都不为空。
        Set<String> keys = params.keySet();
        List<Object> values = new ArrayList<>();
        for (String key: keys) {
            values.add(params.get(key));
        }
        String sql = TableUtil.makeSearchSql(tableId, keys);
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

    public void save(String tableId, Map<String, Object> params) {
        // TODO 这里的keys包含很多不需要的字段，后期测试需要看下能不能把不要的字段都删除掉。
        Set<String> keys = params.keySet();
        List<Object> values = new ArrayList<>();
        values.add("uuid");
        for (String key: keys) {
            values.add(params.get(key));
        }
        String sql = TableUtil.makeAddSql(tableId, keys);
        dao.addOrUpdate(sql, values.toArray());
    }
}
