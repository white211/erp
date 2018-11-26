package com.naswork.erp.config.Shiro;

import com.naswork.erp.common.Constants;
import com.naswork.erp.common.Result;
import com.naswork.erp.entity.User;
import com.naswork.erp.entity.Vo.UserPermission;
import com.naswork.erp.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Collection;

/**
 * @Program: UserRealm
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-24 14:22:05
 **/

public class UserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        UserPermission permission = (UserPermission) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        logger.info("permission的值为:" + permission);
        try {
            logger.info("本用户权限为:" + permission.getPermissionList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            authorizationInfo.addStringPermissions((Collection<String>) permission.getPermissionList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        String loginName = (String) authcToken.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) authcToken.getCredentials());
        Result result = userService.getUser(loginName, password);
        if ( result.getData()== null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = null;
            authenticationInfo = new SimpleAuthenticationInfo(
                    loginName,
                    password,
                    //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                    getName()
            );
        //session中不需要保存密码
            User user = (User) result.getData();
            user.setPassword("");
            //将用户信息放入session中
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        return authenticationInfo;
    }

}






