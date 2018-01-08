package com.liu.web;

import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TUserSubmit;
import com.liu.service.TUserSubmitService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    //用于存放学生申请的信息
    HashMap<String, TUserSubmit> map = new HashMap<>();
    String mark = "";

    //上传文件
    @PostMapping("/onlyUpload")
    public String onlyUpload(@RequestParam("file") MultipartFile file) {
        System.out.println("------upload---------");
        System.out.println(file.getOriginalFilename());
        if (!file.isEmpty()) {
            try {
                // 这里只是简单例子，文件直接输出到项目路径下。
                // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
                // 还有关于文件格式限制、文件大小限制，详见：中配置。
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }


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
}
