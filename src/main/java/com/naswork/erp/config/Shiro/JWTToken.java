package com.naswork.erp.config.Shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Program: JWTToken
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-26 14:58:19
 **/

public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }


}
