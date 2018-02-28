package com.liu.service;
import com.alibaba.fastjson.JSONObject;
import com.liu.core.Result;
import com.liu.model.TUser;
import com.liu.core.Service;


/**
 * Created by liu on 2017/12/27.
 */
public interface TUserService extends Service<TUser> {
    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    TUser getUser(String username, String password);

    /**
     * 登录表单提交
     *
     * @param tUser
     * @return
     */
    Result authLogin(TUser tUser);
}
