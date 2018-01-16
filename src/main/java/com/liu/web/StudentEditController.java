package com.liu.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.core.utils.DeleteFiles;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.liu.core.ProjectConstant.STATIC_RESOURCE;

@RestController
@RequestMapping("/studentEdit")
public class StudentEditController {
    @Resource
    private TUserSubmitService tUserSubmitService;

    @Resource
    private CustomService customService;

    //暂存修改信息
    HashMap<Integer, TUserSubmit> map = new HashMap<>();
    ArrayList<String> deleteFiles = new ArrayList<>();

    /**
     * 清空暂存的数据
     */
    @GetMapping("/clear")
    public void clear(){
        this.map.clear();
        this.deleteFiles.clear();
    }


    /**
     * 查看user的提交
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public Result findSubmitUserInfo(@PathVariable("userId") String userId){
        System.out.println("userSubmit/editUserInfo: => userId="+userId);
        Condition condition = new Condition(TUserSubmit.class);
        condition.createCriteria().andCondition("userid = " +userId);
        List<TUserSubmit> tUserSubmits = tUserSubmitService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(tUserSubmits);
    }


    /**
     * 查看user的提交中的module
     * @param userId
     * @return
     */
    @GetMapping("/findModule/{userId}")
    public Result findMoudle(@PathVariable("userId") String userId){
        System.out.println("findModule/: => userId="+userId);
        List<TModule> tModules = customService.findSubmitModuleByUserId(userId);
        return ResultGenerator.genSuccessResult(tModules);
    }



    /**
     * 删除某一行
     * @param tus
     */
    @PostMapping("/deleteRow")
    public void deleteRow(@RequestBody TUserSubmit tus){
        int id = tus.getId();
        tus.setDelete(true);
        if(map.get(id) != null){
            map.get(id).setDelete(true);
        }
        else{
            map.put(id,tus);
        }
    }

    /**
     * C删除某个文件
     * @param reqMap
     */
    @PostMapping("/deleteFile")
    public void deleteFile(@RequestBody Map<String,Object> reqMap){
        TUserSubmit tus = JSONObject.toJavaObject((JSON) reqMap.get("data"),TUserSubmit.class);
        int id = tus.getId();
        String deleteFileName = reqMap.get("fileName").toString();
        deleteFiles.add(deleteFileName);
        if(map.get(id) != null){
            map.get(id).removeFileName(deleteFileName);
        }
        else{
            map.put(id,tus);
        }


        Result r = new Result();
        r.setData(tus);
        System.out.println(r.toString());


    }

    /**
     * 上传文件
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        int id = Integer.parseInt(params.getParameter("id"));
        int userId=Integer.parseInt(params.getParameter("userId"));
        int roleId=Integer.parseInt(params.getParameter("roleId"));
        String moduleId=params.getParameter("moduleId");
        String typeId=params.getParameter("typeId");
        System.out.println(" upload ====> " + typeId + "\n" + params.getParameter("standardId") );
        int standardId=Integer.parseInt(params.getParameter("standardId"));

        int creditId=Integer.parseInt(params.getParameter("creditId"));
        int evidenceId=Integer.parseInt(params.getParameter("evidenceId"));
        String fileName = params.getParameter("fileName");
        try{
            //如果map中已经有了数据
            if(map.get(id) != null){
                Result r = new Result();
                for(MultipartFile file : files){
                    map.get(id).setFileMap(file.getOriginalFilename(),file);
                    r.setData(file);
                    //为什么没有toString就是错误的?
                    r.toString();
                }
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
                tUserSubmit.setFile(fileName);
                for(MultipartFile file : files){
                    tUserSubmit.setFileMap(file.getOriginalFilename(),file);
                    Result r = new Result();
                    r.setData(file);
                    r.toString();
                    System.out.println(r);
                }
                map.put(id,tUserSubmit);
            }
            return "upload successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

    /**
     * 改变选择栏的值
     * @param tUserSubmit
     */
    @PostMapping("/changeSelect")
    public void changeSelect(@RequestBody TUserSubmit tUserSubmit) {
        int id = tUserSubmit.getId();
        if( map.get(id) != null ){
            map.get(id).setStandardid(tUserSubmit.getStandardid());
            map.get(id).setCreditid(tUserSubmit.getCreditid());
        }
        else {
            map.put(id,tUserSubmit);
        }
    }


    @PostMapping("/edit")
    public Result edit(){
        try{
            String filePath = "";

            //循环map
            for(Map.Entry<Integer,TUserSubmit> entry : map.entrySet() ){
                TUserSubmit tus = entry.getValue();
                filePath = System.getProperty("user.dir") + STATIC_RESOURCE + "/" + tus.getUserid().toString();
                //删除记录
                if( tus.getDelete() ){
                    String[] strTemp  = tus.getFile().split("####");
                    for(int i=0;i<strTemp.length;i++){
                        System.out.println("edit === >" + strTemp[i] + "\n");
                        deleteFiles.add(strTemp[i]);
                    }
                    tUserSubmitService.deleteById(tus.getId());
                }
                //更新记录
                else{
                    tus.writeFile();
                    tUserSubmitService.update(tus);
                }
            }

            //删除文件
            if(!deleteFiles.isEmpty()){
                DeleteFiles dfs = new DeleteFiles();
                System.out.println("deleteFile===>" + filePath + "\n" + deleteFiles.get(0));
                dfs.deleteFilesInMap(deleteFiles,filePath);
            }
            map.clear();
            deleteFiles.clear();
            return ResultGenerator.genSuccessResult("提交成功");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResultGenerator.genFailResult("提交失败");
        }
    }
}
