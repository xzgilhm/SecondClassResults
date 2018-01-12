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
    String MARK = "";

    @GetMapping("/clearMap")
    public void clearMap(){
        String userId = "";
        ArrayList<String> files = new ArrayList<>();
        if(map != null){
            for(TUserSubmit tus : map.values()){
                userId = tus.getUserid().toString();
                System.out.println("cleatMap: " + tus.getFile());
                String[] tempStr = tus.getFile().split("####");
                for(int i=0;i<tempStr.length;i++){
                    files.add(tempStr[i]);
                    System.out.println("clearMap2 " + tempStr[i]);
                }
            }
            DeleteFiles dfs = new DeleteFiles();
            String filePath = System.getProperty("user.dir") + STATIC_RESOURCE + "/" + userId;
            dfs.deleteFilesInMap(files,filePath);
            System.out.println(filePath);
        }


        this.map.clear();
    }
    /**
     *点击上传的响应
     *第一次点击和之后的点击都在维护map
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

//    @PostMapping("changeSelect")
//    public String changeSelect(@RequestBody TUserSubmit tUserSubmit){
//        if(tUserSubmit.getFile().equals("")){
//            tUserSubmit.setFile("");
//            System.out.println("changeSelect" + tUserSubmit.getFile());
//        }
//        else{
//            tUserSubmit.setFile(System.getProperty("user.dir")+"/src/main/resources/static/images/" + tUserSubmit.getFile());
//        }
//        Result r = new Result();
//        r.setData(tUserSubmit);
//        System.out.println(r.toString());
//        MARK = tUserSubmit.getUserid() + "+" + tUserSubmit.getModuleid() + "+" + tUserSubmit.getTypeid();
//        System.out.println(MARK);
////        map.put(MARK,tUserSubmit);
//        return "xx";
//    }

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
        System.out.println(mark + "\n" + fileName);

        String filePath = System.getProperty("user.dir") + STATIC_RESOURCE + "/" + map.get(mark).getUserid();
        System.out.println(filePath);
        DeleteFiles dfs = new DeleteFiles();
        ArrayList<String>  strArray = new ArrayList<String> ();
        strArray.add(mark + "&&" + fileName);
        dfs.deleteFilesInMap(strArray,filePath);
        map.get(mark).deleteName(mark,fileName);
        return "yy";
    }
    //点击submit后的响应
    @GetMapping("/submit")
    public Result submit(){
        try{
            for(Map.Entry<String,TUserSubmit> entry : map.entrySet() ){
                TUserSubmit tus = entry.getValue();
                Result r = new Result();
                r.setData(entry.getValue());
                System.out.println(r.toString());
                tUserSubmitService.save(tus);
            }
            map.clear();
            return ResultGenerator.genSuccessResult("提交成功");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResultGenerator.genFailResult("提交失败");
        }
    }

    //点击修改后的操作
    @GetMapping("/edit/update")
    public Result update(){
//        FileWithByte fwb = new FileWithByte();
//        String filePath = System.getProperty("user.dir")+"/src/main/resources/static/images";
//        try {
//            for(Map.Entry<String,List<TUserSubmit>> entry : map.entrySet() ){
//                List<TUserSubmit> tus = entry.getValue();
//                System.out.println("------/edit/update-----");
//                FileWithByte fwb = new FileWithByte();
//                String filePath = System.getProperty("user.dir")+"/src/main/resources/static/images";
//                String fileName = tus.getUserid() + tus.getModuleid() + tus.getTypeid() + ".jpg";
//                if(tus.getFileByte() != null){
//                    fwb.getFile(tus.getFileByte(),filePath,fileName);
//                }
//                tus.setFile(filePath+"/" +fileName);
//                Result r = new Result();
//                r.setData(tus);
//                System.out.println(r.toString());
//                String sql = "userid="+tus.getUserid()+" AND moduleid="+ tus.getModuleid() +" AND typeid="+tus.getTypeid();
//                Condition condition = new Condition(TUserSubmit.class);
//                condition.createCriteria().andCondition(sql);
//                tUserSubmitService.updateByCondition(condition,tus);
//            }
//            map.clear();
            return ResultGenerator.genSuccessResult("提交成功");
//        }catch (Exception e){
//            return ResultGenerator.genFailResult("提交失败");
//        }
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
