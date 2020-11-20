package com.servicesilo.one.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.servicesilo.one.model.ServiceNodeLink;
import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.model.ServiceTableCol;
import com.servicesilo.one.model.ServiceUser;
import com.servicesilo.one.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RedisUtil {
    private static final String TOKEN_STR = "TOKEN_";
    private static final String LINK_STR = "LINK_";
    private static final String TABLE_STR = "TABLE_";

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.template = redisTemplate;
    }

    @Autowired
    private RedisTemplate<String, Object> template;

    @Autowired
    private CommonService commonService;

    public ServiceUser getUser(String token) {
        Object user = template.opsForValue().get(TOKEN_STR + token);
        if (user == null) {
            String userId;
            try {
//                userId = JWT.decode(token).getAudience().get(0);
                userId = "1abc2";
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
            String sql = "select * from service_user where uuid = ? ";
            Map<String, Object> temp = commonService.findOneBySql(sql, userId);
            if (temp == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            } else {
                user = ServiceUser.builder().
                        groupId(obj2Str(temp.get("group_id"))).
                        uuid(obj2Str(temp.get("uuid"))).
                        loginName(obj2Str(temp.get("login_name"))).
                        realName(obj2Str(temp.get("real_name"))).
                        roleId(obj2Str(temp.get("role_id"))).
                        build();
            }
            setUser((ServiceUser)user, token);
        }
        return (ServiceUser)user;
    }

    public void setUser(ServiceUser user, String token) {
        template.opsForValue().set(TOKEN_STR + token, user);
    }

    public ServiceNodeLink getLink(String linkId) {
        Object link = template.opsForValue().get(LINK_STR + linkId);
        if (link == null) {
            String sql = "select * from service_node_link where (uuid = ? or uuid in (select sub_link_id from service_link_r where link_id = ?))";
            List<Map<String, Object>> temps = commonService.findBySql(sql, linkId, linkId);
            List<ServiceNodeLink> subLinks = new ArrayList<>();
            for (Map<String, Object> temp: temps) {
                if (temp.get("uuid").toString().equals(linkId)) {
                    link = ServiceNodeLink.builder()
                            .linkName(obj2Str(temp.get("link_name")))
                            .linkOptCols(obj2Str(temp.get("link_opt_cols")))
                            .linkShowCols(obj2Str(temp.get("link_show_cols")))
                            .linkTo(obj2Str(temp.get("link_to")))
                            .linkFunc(obj2Str(temp.get("link_func")))
                            .linkType(obj2Str(temp.get("link_type")))
                            .nodeId(obj2Str(temp.get("node_id")))
                            .roleId(obj2Str(temp.get("role_id")))
                            .tableId(obj2Str(temp.get("table_id")))
                            .userId(obj2Str(temp.get("user_id")))
                            .uuid(obj2Str(temp.get("uuid")))
                            .build();
                } else {
                    subLinks.add(ServiceNodeLink.builder()
                            .linkName(obj2Str(temp.get("link_name")))
                            .linkOptCols(obj2Str(temp.get("link_opt_cols")))
                            .linkShowCols(obj2Str(temp.get("link_show_cols")))
                            .linkTo(obj2Str(temp.get("link_to")))
                            .linkFunc(obj2Str(temp.get("link_func")))
                            .linkType(obj2Str(temp.get("link_type")))
                            .nodeId(obj2Str(temp.get("node_id")))
                            .roleId(obj2Str(temp.get("role_id")))
                            .tableId(obj2Str(temp.get("table_id")))
                            .userId(obj2Str(temp.get("user_id")))
                            .uuid(obj2Str(temp.get("uuid")))
                            .build());
                }
            }
            assert link != null;
            ((ServiceNodeLink) link).setSubLinks(subLinks);
            setLink((ServiceNodeLink) link);
        }
        return (ServiceNodeLink)link;
    }

    public void setLink (ServiceNodeLink link) {
        template.opsForValue().set(LINK_STR + link.getUuid(), link);
    }

    public void setTable (ServiceTable table) {
        template.opsForValue().set(TABLE_STR + table.getUuid(), table);
    }

    public ServiceTable getTable (String tableId) {
        Object tab = template.opsForValue().get(TABLE_STR + tableId);
        if (tab == null) {
            String sql = "select * from service_table where uuid = ? ";
            Map<String, Object> temp = commonService.findOneBySql(sql, tableId);
            tab = ServiceTable.builder()
                    .tableCode(obj2Str(temp.get("table_code")))
                    .tableName(obj2Str(temp.get("table_name")))
                    .tableType(obj2Str(temp.get("table_type")))
                    .uuid(temp.get("uuid").toString()).build();
            String sqlCols = "select * from service_table_col where table_id = ? ";
            List<Map<String, Object>> ls = commonService.findBySql(sqlCols, tableId);
            List<ServiceTableCol> cols = new ArrayList<>();
            for (Map<String, Object> col: ls) {
                cols.add(ServiceTableCol
                        .builder()
                        .colCode(obj2Str(col.get("col_code")))
                        .colName(obj2Str(col.get("col_name")))
                        .colType(obj2Str(col.get("col_type")))
                        .searchType(obj2Str(col.get("search_type")))
                        .colLength(obj2Str(col.get("col_length")))
                        .orderNum((int)col.get("order_num"))
                        .uuid(obj2Str(col.get("uuid")))
                        .tableId(obj2Str(col.get("table_id")))
                        .colPlaceholder(obj2Str(col.get("table_id")))
                        .build());
            }
            ((ServiceTable)tab).setCols(cols);

            setTable((ServiceTable)tab);
        }
        return (ServiceTable)tab;
    }

    private String obj2Str(Object obj) {
        return obj == null? null: obj.toString();
    }
}

