package com.servicesilo.one.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenService {

    public String getToken(Map<String, Object> user) {
        String token="";
        token= JWT.create().withAudience(user.get("uuid").toString())
                .sign(Algorithm.HMAC256(user.get("password").toString()));
        return token;
    }

}
