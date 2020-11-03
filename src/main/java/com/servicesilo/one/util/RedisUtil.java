package com.servicesilo.one.util;

import com.servicesilo.one.model.ServiceNodeLink;
import com.servicesilo.one.model.ServiceUser;

public class RedisUtil {

    public static ServiceUser getUser(String token) {

        return ServiceUser.builder().build();
    }

    public static ServiceNodeLink getLink(String linkId) {

        return ServiceNodeLink.builder().build();
    }
}
