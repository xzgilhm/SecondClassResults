package com.liu.web;


import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TModule;
import com.liu.model.TUserSubmit;
import com.liu.service.CustomService;
import com.liu.service.TUserSubmitService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/studentEdit")
public class StudentEditController {
    @Resource
    private TUserSubmitService tUserSubmitService;

    @Resource
    private CustomService customService;
    //用于存放学生申请的信息
    HashMap<String, TUserSubmit> map = new HashMap<>();
    ArrayList<String> deleteFileList  = new ArrayList<>();

    //查看user的提交
    @GetMapping("/{userId}")
    public Result findSubmitUserInfo(@PathVariable("userId") String userId){
        System.out.println("userSubmit/editUserInfo: => userId="+userId);
        Condition condition = new Condition(TUserSubmit.class);
        condition.createCriteria().andCondition("userid = " +userId);
        List<TUserSubmit> tUserSubmits = tUserSubmitService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(tUserSubmits);
    }

    //查看user的提交中的module
    @GetMapping("/findModule/{userId}")
    public Result findMoudle(@PathVariable("userId") String userId){
        System.out.println("/edit/findModule/: => userId="+userId);
        List<TModule> tModules = customService.findSubmitModuleByUserId(userId);
        return ResultGenerator.genSuccessResult(tModules);
    }

    /**
     *改变选择栏的值
     */
    @PostMapping("/changeSelect")
    public String changeSelect(@RequestBody TUserSubmit tUserSubmit) {
        String mark = tUserSubmit.getUserid() + tUserSubmit.getModuleid() + tUserSubmit.getTypeid();
        if( map.get(mark) != null ){
            map.get(mark).setStandardid(tUserSubmit.getStandardid());
            map.get(mark).setCreditid(tUserSubmit.getCreditid());
        }
        else {
            map.put(mark,tUserSubmit);
        }
        return "changeSelect";
    }

}
