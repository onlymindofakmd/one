package com.servicesilo.one.service;

import com.servicesilo.one.datasource.CommonDAO;
import com.servicesilo.one.dict.TrueOrFalse;
import com.servicesilo.one.model.Line;
import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.model.ServiceTableCol;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListService implements IService {
    private static ListService me = new ListService();

    public static ListService getInstance() {return me;}

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private CommonDAO dao;

    @Override
    public CommonRet execute(Line line, ServiceUser user, Map<String, Object> params) {
        String preLine = params.get("preLine").toString();
        ServiceTable table = redisUtil.getTable(line.getTableId());
        String reqCols = line.getReqCols();
        if (TrueOrFalse.TRUE.getCode().equals(preLine)) {
            String resCols = line.getResCols();
            ServiceTable showTable = ServiceTable.builder()
                    .tableCode(table.getTableCode())
                    .tableName(table.getTableName())
                    .tableType(table.getTableType())
                    .build();
            List<ServiceTableCol> cs = new ArrayList<>();
            for (ServiceTableCol col: table.getCols()) {
                if (resCols.contains(col.getColCode())) {
                    cs.add(col);
                    if (!reqCols.contains(col.getColCode())) {
                        col.setSearchType(null);
                    }
                }
            }
            showTable.setCols(cs);
            return RetUtil.success(showTable);
        }
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (String key: reqCols.split(",")) {
            if (params.get(key) != null) {
                keys.add(key);
                values.add(params.get(key));
            }
        }
        List<Map<String, Object>> ls = dao.
                find(TableUtil.makeSearchSql(line, table, keys, user), values.toArray());
        return RetUtil.success(ls);
    }
}
