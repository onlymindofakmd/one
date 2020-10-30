package com.servicesilo.one.controller.user;

import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RetUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping(value = "/login")
    @ResponseBody
    public CommonRet login(@RequestParam String loginName, @RequestParam String password) {
        Map<String, String> m = new HashMap<>();
        m.put("accessToken", "1234567890");
        m.put("mainPage", "/");
        return RetUtil.success(m);
    }

}
