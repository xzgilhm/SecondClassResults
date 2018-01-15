package com.liu.web;

import com.liu.core.FileWithByte;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.core.utils.DeleteFiles;
import com.liu.model.CustomMarkModel;
import com.liu.model.TModule;
import com.liu.model.TUser;
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

import static com.liu.core.ProjectConstant.STATIC_RESOURCE;


@RestController
@RequestMapping("/userSubmit")
public class UserSubmitController {
    @Resource
    private TUserSubmitService tUserSubmitService;

    @Resource
    private CustomService customService;
    //用于存放学生申请的信息
    HashMap<String, TUserSubmit> map = new HashMap<>();
    ArrayList<String> deleteFileList  = new ArrayList<>();

    /**
     * 刷新页面时的操作
     * 1.将已经写入磁盘的文件删除
     * 2.将所维护的map和deleteFileList清空
     */
    @GetMapping("/clearMap")
    public void clearMap(){
        String userId = "";
        ArrayList<String> files = new ArrayList<>();
        if(map != null){
            for(TUserSubmit tus : map.values()){
                userId = tus.getUserid().toString();
                System.out.println("cleatMap: " + tus.getFile());
                if(tus.getFile() != null){
                    String[] tempStr = tus.getFile().split("####");
                    for(int i=0;i<tempStr.length;i++){
                        files.add(tempStr[i]);
                        System.out.println("clearMap2 " + tempStr[i]);
                    }
                }

            }
            DeleteFiles dfs = new DeleteFiles();
            String filePath = System.getProperty("user.dir") + STATIC_RESOURCE + "/" + userId;
            dfs.deleteFilesInMap(files,filePath);
        }

        this.deleteFileList.clear();
        this.map.clear();
    }

    /**
     * 点击上传的响应
     * 取到文件的所有信息,将其写入map中
     * 并且此时已经将文件写到磁盘中了
     */
    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        int userId=Integer.parseInt(params.getParameter("userId"));
        int roleId=Integer.parseInt(params.getParameter("roleId"));
        String moduleId=params.getParameter("moduleId");
        String typeId=params.getParameter("typeId");
        int standardId=Integer.parseInt(params.getParameter("standardId"));
        int creditId=Integer.parseInt(params.getParameter("creditId"));
        int evidenceId=Integer.parseInt(params.getParameter("evidenceId"));
        String mark = userId  + moduleId  + typeId;
        try{
            //如果map中已经有了对应的数据
            if(map.get(mark) != null){
                map.get(mark).setFileByte(files,mark,userId);
            }
            else{
                TUserSubmit tUserSubmit = new TUserSubmit();
                tUserSubmit.setUserid(userId);
                tUserSubmit.setRoleid(roleId);
                tUserSubmit.setModuleid(moduleId);
                tUserSubmit.setTypeid(typeId);
                tUserSubmit.setStandardid(standardId);
                tUserSubmit.setCreditid(creditId);
                tUserSubmit.setEvidenceid(evidenceId);
                tUserSubmit.setFileByte(files,mark,userId);
                map.put(mark,tUserSubmit);
            }
            return "upload successful";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }

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

    /**
     *删除一个文件
     */
    @PostMapping("/removeFileList")
    public String changeFileList(@RequestBody CustomMarkModel cmm) {
        String mark = cmm.getMark();
        String fileName = cmm.getFileName();
        deleteFileList.add(mark + "&&" + fileName);
        map.get(mark).deleteName(mark,fileName);
        return "yy";
    }
    //点击submit后的响应
    @GetMapping("/submit")
    public Result submit(){
        try{
            Integer userId = -1;
            for(Map.Entry<String,TUserSubmit> entry : map.entrySet() ){
                TUserSubmit tus = entry.getValue();
                userId = tus.getUserid();
                tUserSubmitService.save(tus);
            }
            //删除文件
            String filePath = System.getProperty("user.dir") + STATIC_RESOURCE + "/" + userId.toString();
            System.out.println(filePath);
            DeleteFiles dfs = new DeleteFiles();
            dfs.deleteFilesInMap(deleteFileList,filePath);
            deleteFileList.clear();
            map.clear();
            return ResultGenerator.genSuccessResult("提交成功");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResultGenerator.genFailResult("提交失败");
        }
    }

}
