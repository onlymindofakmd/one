package com.servicesilo.one.util;

import com.servicesilo.one.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableUtil {

    @Autowired
    private RedisUtil redisUtil;

    public static String makeAddSql(ServiceTable table, List<String> keys) {
        StringBuilder sb = new StringBuilder();
        assert table != null;
        StringBuilder fields = new StringBuilder("uuid");
        StringBuilder vs = new StringBuilder("?");
        // 这里keys不做校验，keys的校验在拦截器中完成，后面在写。
        for(String key: keys) {
            fields.append(" , ").append(key);
            vs.append(" , ?");
        }

        sb.append("insert into ")
                .append(table.getTableCode())
                .append("(")
                .append(fields.toString())
                .append(") values(")
                .append(vs.toString())
                .append(") ");
        return sb.toString();
    }

    public static String makeSearchSql(ServiceNodeLink link,
                                       ServiceTable table,
                                       List<String> keys,
                                       ServiceUser user) {
        String tableId = link.getTableId();
        String status = link.getNodeId();
        String showCols = link.getLinkShowCols();
        assert table != null;
        String tableName =  table.getTableCode();
        List<ServiceTableCol> cols = table.getCols();
        StringBuilder sb = new StringBuilder();
        sb.append("select m.uuid, ").append(showCols);

        Map<String , String> filterMap = new HashMap<>();

        for (ServiceTableCol col: cols) {
            if (keys.contains(col.getColCode())) {
                filterMap.put(col.getColCode(), col.getSearchType());
            }
        }

        sb.append(" from ").append(tableName)
                .append(" as m left join service_workflow_record swr on m.uuid = swr.data_id ");

        sb.append(" where 1=1 ");

        if (!StringUtils.isEmpty(status)) {
            sb.append(" and swr.node_id =  ").append(status);
        }

        for (String key: keys) {
            String searchType = filterMap.get(key);
            if (searchType == null ){
                continue;
            }
            sb.append(" and ").append(key);
            switch (searchType) {
                case "1":
                    sb.append(" = ? ");
                case "2":
                    sb.append(" %?%");
                case "3":
                    sb.append(" %?");
                case "4":
                    sb.append(" ?%");
                case "5":
                    sb.append(" < ?");
                case "6":
                    sb.append(" <= ?");
                case "7":
                    sb.append(" > ?");
                case "8":
                    sb.append(" >= ?");
            }

        }
        //数据权限
        sb.append(PermissionUtil.getPermission(user));

        return sb.toString();
    }

    public static String makeSearchSql(Line lin,
                                        ServiceTable table,
                                        List<String> keys,
                                        ServiceUser user) {

        return "";
    }

    public static String makeSearchSqlWithPage(Line lin,
                                       ServiceTable table,
                                       List<String> keys,
                                       ServiceUser user) {

        return "";
    }

}
