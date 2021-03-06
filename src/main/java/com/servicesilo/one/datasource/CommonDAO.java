package com.servicesilo.one.datasource;

import com.servicesilo.one.model.ServiceTableCol;
import com.servicesilo.one.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommonDAO {

    @Autowired
    protected JdbcTemplate template;

    public void addOrUpdate(String sql, Object ...params) {
        template.update(sql, params);
    }

    public List<Map<String, Object>> find(String sql, Object ...args) {
        return template.queryForList(sql, args);
    }

    public Map<String, Object> findOne(String sql, Object ...args) {
        return template.queryForMap(sql, args);
    }

}
