package com.liu.web;

import com.liu.model.TUser;
import com.liu.service.TRoleService;
import com.liu.service.TUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 73559 on 2017/12/27.
 */
@Controller
public class UserController {

    @Resource
    private TUserService tUserService;

    @Resource
    private TRoleService tRoleService;

    @GetMapping(value = "/hello")
    public String say(){
        return "login";
    }

    //登陆
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(TUser userlogin)  {
        System.out.println("---------login.do ------------------");
//        TUser user = tUserService.findByName(userlogin.getName());

//       System.out.println(user.getPassword());

        return "-1";
    }
}
