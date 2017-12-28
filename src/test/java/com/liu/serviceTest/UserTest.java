package com.liu.serviceTest.impl;

import com.liu.model.TUser;
import com.liu.service.TUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 73559 on 2017/12/27.
 */
public class UserTest {

    @Autowired
    TUserService userloginService;



    @Test
    public void findByName() throws Exception {
        TUser t = userloginService.findBy("name","liu1");
        System.out.println(t.getPassword());
    }

}