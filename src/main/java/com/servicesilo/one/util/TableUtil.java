package com.servicesilo.one.util;

import com.servicesilo.one.dict.TrueOrFalse;
import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.model.ServiceTableCol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TableUtil {

    public static Object getColValue(String colType) {
        return null;
    }

    public static ServiceTable getTable(String tableId) {

        return null;
    }

    public static String makeAddSql(String tableId, Set<String> keys) {
        StringBuilder sb = new StringBuilder();
        ServiceTable table = getTable(tableId);
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

    public static String makeSearchSql(String tableId, Set<String> keys) {
        ServiceTable table = getTable(tableId);
        assert table != null;
        String tableName =  table.getTableCode();
        List<ServiceTableCol> cols = table.getCols();
        StringBuilder sb = new StringBuilder();
        sb.append("select uuid");

        Map<String , String> filterMap = new HashMap<>();

        for (ServiceTableCol col: cols) {
            if (TrueOrFalse.FALSE.getCode().equals(col.getHideInSearch())) {
                filterMap.put(col.getColCode(), col.getSearchType());
            }

            if (TrueOrFalse.FALSE.getCode().equals(col.getHideInTable())) {
                sb.append(", ");
                sb.append(col.getColCode());
            }
        }

        sb.append(" from ").append(tableName).append(" where 1=1");

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

        return sb.toString();
    }

}
