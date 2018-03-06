package com.liu.web;

import com.liu.configurer.shiro.ShiroConfiguration;
import com.liu.core.FileWithByte;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.core.utils.DeleteFiles;
//import com.liu.model.CustomMarkModel;
import com.liu.model.TModule;
import com.liu.model.TUser;
import com.liu.model.TUserSubmit;
import com.liu.service.CustomService;
import com.liu.service.TUserSubmitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserSubmitController.class);

    @Resource
    private TUserSubmitService tUserSubmitService;


    //用于存放学生申请的信息
    HashMap<String, TUserSubmit> map = new HashMap<>();

    /**
     * 刷新页面和提交后的操作
     */
    @GetMapping("/clearMap")
    public void clearMap(){
        logger.info("-----------------刷新页面--------------");
        this.map.clear();
    }

    /**
     * 点击上传的响应
     * 取到文件的所有信息,将其写入map中
     */

    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        TUserSubmit tus = new TUserSubmit(params);
        String mark = tus.getUserid()  + tus.getModuleid()  + tus.getTypeid();
        try{
            //如果map中已经有了数据
            if(map.get(mark) != null){

                for(MultipartFile file : files){
                    logger.info("已有数据===========" );

                    map.get(mark).setFileMap(file.getOriginalFilename(),file);

                    Result r = new Result();
                    r.setData(file);
                    r.toString();
                }
            }
            else{
                logger.info("暂无数据===========");
                for(MultipartFile file : files){
                    tus.setFileMap(file.getOriginalFilename(),file);

                    Result r = new Result();
                    r.setData(file);
                    r.toString();
                }
                map.put(mark,tus);
            }
            return "upload successful";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }



    /**
     *改变选择栏的值
     */
    @PostMapping("/changeSelect")
    public void changeSelect(@RequestBody TUserSubmit tUserSubmit) {
        String mark = tUserSubmit.getUserid() + tUserSubmit.getModuleid() + tUserSubmit.getTypeid();
        if( map.get(mark) != null ){
            map.get(mark).setStandardid(tUserSubmit.getStandardid());
            map.get(mark).setCreditid(tUserSubmit.getCreditid());
        }
        else {
            map.put(mark,tUserSubmit);
        }
    }

    /**
     *删除一个文件
     */
    @PostMapping("/removeFileList")
    public void changeFileList(@RequestBody Map<String,String> reqMap) {
        String mark = reqMap.get("mark").toString();
        String fileName = reqMap.get("fileName").toString();

        logger.info("删除文件对应数据 mark:" + mark + "\n"+ "filename:" + fileName);
        //删除改文件名对应的file
        map.get(mark).getFileMap().remove(fileName);
        map.get(mark).removeFileName(fileName);
    }


    /**
     *提交文件
     *将map中的数据存入数据库并将文件写入磁盘
     */
    @GetMapping("/submit")
    public Result submit(){
        try{
            for( Map.Entry<String,TUserSubmit> entry : map.entrySet() ){
                TUserSubmit tus = entry.getValue();
                tus.writeFile();

                tUserSubmitService.save(tus);
            }
            map.clear();
            return ResultGenerator.genSuccessResult("提交成功");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResultGenerator.genFailResult("提交失败");
        }
    }

}
