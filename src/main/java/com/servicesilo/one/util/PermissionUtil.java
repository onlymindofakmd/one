package com.servicesilo.one.util;

import com.servicesilo.one.model.ServiceUser;

public class PermissionUtil {

    public static String getPermission(ServiceUser user) {
        return " and (swr.role_id = " +
                user.getRoleId() +
                " or swr.user_id = " +
                user.getUuid() +
                " ) ";
    }

}
