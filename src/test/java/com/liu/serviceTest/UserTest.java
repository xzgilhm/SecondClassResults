package com.liu.serviceTest;

import com.liu.Tester;
import com.liu.core.Result;
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
public class UserTest extends Tester {

    @Autowired
    TUserService userloginService;



    @Test
    public void findByName() throws Exception {
        TUser t = userloginService.findBy("name","liu1");
        System.out.println(t.getPassword());
    }


    @Test
    public void getUser() {
        TUser t = userloginService.getUser("liu1","123");
        Result r = new Result();
        r.setData(t);
        System.out.println(r.toString());
    }


}