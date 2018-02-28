package com.liu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.dao.TUserMapper;
import com.liu.model.TUser;
import com.liu.service.TUserService;
import com.liu.core.AbstractService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/12/27.
 */
@Service
@Transactional
public class TUserServiceImpl extends AbstractService<TUser> implements TUserService {
    @Resource
    private TUserMapper tUserMapper;

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public JSONObject getUser(String username, String password) {
        return tUserMapper.getUser(username, password);
    }


    /**
     * 登录表单提交
     *
     * @param tUser
     * @return
     */
    @Override
    public Result authLogin(TUser tUser) {
        String username = tUser.getName();
        String password = tUser.getPassword();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            return ResultGenerator.genSuccessResult(this.getUser(username,password));
        } catch (AuthenticationException e) {
            return ResultGenerator.genFailResult("fail");
        }
    }

}
