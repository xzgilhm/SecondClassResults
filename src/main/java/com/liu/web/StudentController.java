package com.liu.web;


import com.liu.core.FileWithByte;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TUser;
import com.liu.model.TUserSubmit;
import com.liu.service.TUserSubmitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private TUserSubmitService tUserSubmitService;

    //点击submit后的响应
    @PostMapping("/submit")
    public Result submit(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        Result r = new Result();
        r.setData(files);
        System.out.println(r.toString());
        TUserSubmit tUserSubmit = new TUserSubmit();
        int userId=Integer.parseInt(params.getParameter("userId"));
        System.out.println("userId:"+userId);
        int roleId=Integer.parseInt(params.getParameter("roleId"));
        String moduleId=params.getParameter("moduleId");
        String typeId=params.getParameter("typeId");
        int standardId=Integer.parseInt(params.getParameter("standardId"));
        int creditId=Integer.parseInt(params.getParameter("creditId"));
        int evidenceId=Integer.parseInt(params.getParameter("evidenceId"));
        String mark = userId + moduleId + typeId;
        tUserSubmit.setUserid(userId);
        tUserSubmit.setRoleid(roleId);
        tUserSubmit.setModuleid(moduleId);
        tUserSubmit.setTypeid(typeId);
        tUserSubmit.setStandardid(standardId);
        tUserSubmit.setCreditid(creditId);
        tUserSubmit.setEvidenceid(evidenceId);
//        tUserSubmit.setFileByte(files,mark);
        tUserSubmitService.save(tUserSubmit);
        return ResultGenerator.genSuccessResult(files);
    }
}
