package com.liu.dao;

import com.alibaba.fastjson.JSONObject;
import com.liu.core.Mapper;
import com.liu.model.TUser;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper extends Mapper<TUser> {
    TUser getUser(@Param("username") String username,@Param("password") String password);
}