package com.liu.web;


import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TUser;
import com.liu.service.TRoleService;
import com.liu.service.TUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by 73559 on 2017/12/27.
 */
@Controller
public class UserController {

    @Resource
    private TUserService tUserService;

    //登陆
    @PostMapping(value = "/login")
    @ResponseBody //返回
    public Result login(@RequestBody TUser userlogin)  {
        System.out.println("---------login ------------------");

        return tUserService.authLogin(userlogin);
    }

    @GetMapping(value = "/getInfo")
    @ResponseBody
    public Result getInfo(){
        System.out.println("----------getInfo-----------------");

        return ResultGenerator.genSuccessResult();
    }

}
