package com.liu.web;

import com.liu.configurer.WebSecurityConfigurer;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TUser;
import com.liu.service.TRoleService;
import com.liu.service.TUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @GetMapping(value = "/login")
    public String say(){
        return "login";
    }




    //登陆,并记录session
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody TUser userlogin, HttpSession session)  {
        System.out.println("---------login.do ------------------");
        System.out.println(userlogin.getName());
        try{
            TUser tuser = tUserService.findBy("name",userlogin.getName());
            if(tuser.getPassword().equals(userlogin.getPassword())){
                // 设置session
                session.setAttribute(WebSecurityConfigurer.SESSION_KEY, tuser.getName());
                System.out.println("---session--"+session.getAttribute(WebSecurityConfigurer.SESSION_KEY)+"\n");
                return ResultGenerator.genSuccessResult(tuser);
            }
            else {
                return ResultGenerator.genFailResult("用户名或者密码不正确");
            }
        } catch (NullPointerException e){
            return ResultGenerator.genFailResult("用户名不存在");
        }
    }

    //移除session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfigurer.SESSION_KEY);
        return "redirect:/login";
    }


    //转向学生
    @GetMapping(value = "/student")
    public String student(@SessionAttribute(WebSecurityConfigurer.SESSION_KEY) String account,Model model){
        model.addAttribute("username",account);
        return "student/application";
    }
}
