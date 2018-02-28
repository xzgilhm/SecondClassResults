package com.liu.configurer.shiro;

import com.alibaba.fastjson.JSONObject;
import com.liu.service.TUserService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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

import java.util.Collection;


public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private TUserService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
//        JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        JSONObject permission = (JSONObject) session.getAttribute("userPermission");
        logger.info("permission的值为:" + permission);
        logger.info("本用户权限为:" + permission.get("permissionList"));
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * UserController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 获取用户名和密码
        String loginName = (String) authcToken.getPrincipal();
        String password = new String((char[]) authcToken.getCredentials());
        logger.info("loginName and password  ===> " + loginName + "\n" + password);
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        logger.info("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //取得当前账号和密码得到的user
        JSONObject user = loginService.getUser(loginName, password);
        logger.info("user value ===> " + user.toJSONString());
        if (user == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getString("name"),
                user.getString("password"),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        //session中不需要保存密码
        user.remove("password");
        //将用户信息放入session中
//        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        SecurityUtils.getSubject().getSession().setAttribute("userInfo", user);
        return authenticationInfo;
    }
}
