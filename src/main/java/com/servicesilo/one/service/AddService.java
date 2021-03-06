package com.servicesilo.one.service;

import com.servicesilo.one.datasource.CommonDAO;
import com.servicesilo.one.dict.TrueOrFalse;
import com.servicesilo.one.model.Line;
import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.model.ServiceTableCol;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RedisUtil;
import com.servicesilo.one.util.RetUtil;
import com.servicesilo.one.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AddService implements IService {
    private static AddService me = new AddService();

    public static AddService getInstance() {
        return me;
    }

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private CommonDAO dao;

    @Override
    public CommonRet execute(Line line, ServiceUser user, Map<String, Object> params) {
        // 这里约定俗成用preLine来区分页面展示请求还是保存请求
        String preLine = params.get("preLine").toString();
        ServiceTable table = redisUtil.getTable(line.getTableId());
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
                }
            }
            showTable.setCols(cs);
            return RetUtil.success(showTable);
        }

        String reqCols = line.getReqCols();
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (String col: reqCols.split(",")) {
            Object val = params.get(col);
            if (val != null && !StringUtils.isEmpty(val.toString())) {
                keys.add(col);
                values.add(val);
            }
        }
        String sql = TableUtil.makeAddSql(table, keys);
        dao.addOrUpdate(sql, values.toArray());
        return RetUtil.success();
    }
}
