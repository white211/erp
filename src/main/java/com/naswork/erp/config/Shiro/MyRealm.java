package com.naswork.erp.config.Shiro;

import com.naswork.erp.entity.User;
import com.naswork.erp.entity.Vo.UserPermission;
import com.naswork.erp.service.UserService;
import com.naswork.erp.utils.jwt.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Program: MyRealm
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-26 14:39:49
 **/
@Service
public class MyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JWTUtil.getUsername(principalCollection.toString());
        User user = (User) userService.getUser(username).getData();
        UserPermission userPermission = (UserPermission) userService.getInfo(username).getData();
        logger.info("用户权限："+userPermission.getPermissionList());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addRole(user.getRole());
//        Set<String> permission = new HashSet<>(Arrays.asList(userPermission.getPermission().split(",")));
        simpleAuthorizationInfo.addRole(userPermission.getRoleName());
        simpleAuthorizationInfo.addStringPermissions((Collection<String>) userPermission.getPermissionList());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = (User) userService.getUser(username).getData();
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        if (! JWTUtil.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}

