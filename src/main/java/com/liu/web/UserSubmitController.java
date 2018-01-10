package com.liu.web;

import com.liu.core.FileWithByte;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TModule;
import com.liu.model.TUserSubmit;
import com.liu.service.CustomService;
import com.liu.service.TUserSubmitService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/userSubmit")
public class UserSubmitController {
    @Resource
    private TUserSubmitService tUserSubmitService;

    @Resource
    private CustomService customService;

    //用于存放学生申请的信息
    HashMap<String, TUserSubmit> map = new HashMap<>();
    String mark = "";


    //在学生申请界面中点击上传文件后的响应
    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        //获取参数
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        int userId=Integer.parseInt(params.getParameter("userId"));
        System.out.println("userId:"+userId);
        int roleId=Integer.parseInt(params.getParameter("roleId"));
        String moduleId=params.getParameter("moduleId");
        String typeId=params.getParameter("typeId");
        int standardId=Integer.parseInt(params.getParameter("standardId"));
        int creditId=Integer.parseInt(params.getParameter("creditId"));
        int evidenceId=Integer.parseInt(params.getParameter("evidenceId"));
        mark = userId + "+" + moduleId + "+" + typeId;
        TUserSubmit tUserSubmit = new TUserSubmit();
        tUserSubmit.setUserid(userId);
        tUserSubmit.setRoleid(roleId);
        tUserSubmit.setModuleid(moduleId);
        tUserSubmit.setTypeid(typeId);
        tUserSubmit.setStandardid(standardId);
        tUserSubmit.setCreditid(creditId);
        tUserSubmit.setEvidenceid(evidenceId);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                tUserSubmit.setFile(bytes);
            } catch (Exception e) {
                return "You failed to upload => " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }

        //把数据存到map中
        map.put(mark,tUserSubmit);
        return "upload successful";
    }


    //点击submit后的响应
    @GetMapping("/submit")
    public Result submit(){
        List<TUserSubmit> tUserSubmits = new ArrayList<>();
        for(Map.Entry<String,TUserSubmit> entry : map.entrySet() ){
            tUserSubmits.add(entry.getValue());
        }
        try{
            //清空map
            map.clear();
            tUserSubmitService.save(tUserSubmits);
            return ResultGenerator.genSuccessResult("提交成功");
        }catch (Exception e){
            return ResultGenerator.genFailResult("提交失败");
        }

    }

    //点击修改后的操作
    @GetMapping("/edit/update")
    public Result update(){
        try {
            for(Map.Entry<String,TUserSubmit> entry : map.entrySet() ){
                TUserSubmit tus = entry.getValue();
                String sql = "userid="+tus.getUserid()+" AND moduleid="+ tus.getModuleid() +" AND typeid="+tus.getTypeid();
                Condition condition = new Condition(TUserSubmit.class);
                condition.createCriteria().andCondition(sql);
                tUserSubmitService.updateByCondition(condition,tus);
            }
            map.clear();
            return ResultGenerator.genSuccessResult("提交成功");
        }catch (Exception e){
            return ResultGenerator.genFailResult("提交失败");
        }
    }
    //查看user的提交
    @GetMapping("/editUserInfo/{userId}")
    public Result findSubmitUserInfo(@PathVariable("userId") String userId){
        System.out.println("userSubmit/editUserInfo: => userId="+userId);
        Condition condition = new Condition(TUserSubmit.class);
        condition.createCriteria().andCondition("userid = " +userId);
        List<TUserSubmit> tUserSubmits = tUserSubmitService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(tUserSubmits);
    }

    //查看user的提交中的module
    @GetMapping("/edit/findModule/{userId}")
    public Result findMoudle(@PathVariable("userId") String userId){
        System.out.println("/edit/findModule/: => userId="+userId);
        List<TModule> tModules = customService.findSubmitModuleByUserId(userId);
        return ResultGenerator.genSuccessResult(tModules);
    }
}
