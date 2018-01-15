package com.liu.model;

import com.liu.core.FileWithByte;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.liu.core.ProjectConstant.STATIC_RESOURCE;

@Table(name = "t_user_submit")
public class TUserSubmit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userid;

    private Integer roleid;

    /**
     * 模块名称
     */
    private String moduleid;

    /**
     * 类型
     */
    private String typeid;

    /**
     * 项目标准
     */
    private Integer standardid;

    /**
     * 学分
     */
    private Integer creditid;

    /**
     * 认定依据
     */
    private Integer evidenceid;

    /**
     * 证明所需的相关资料
     */
    private String file;


    @Transient
    /**
     * 写成map主要是为了删除文件方便
     */
    private HashMap<String,MultipartFile> fileMap = new HashMap<>();

    public void setFileMap(HashMap<String, MultipartFile> fileMap) {
        this.fileMap = fileMap;
    }

    public HashMap<String, MultipartFile> getFileMap() {
        return fileMap;
    }

    public void setFileMap(String fileName,MultipartFile file) throws IOException {
        System.out.println("setFileMap=====>" + fileName);
        System.out.println("setFileMap=====>" + file.getBytes().toString());
        this.fileMap.put(fileName,file);
        if(this.file == null){
            this.file = fileName + "####";
        }
        else{
            this.file += fileName + "####";
        }
    }

    public void removeFileName(String fileName){
        System.out.println("removeFileName =====> " + fileName + "\n" + this.file);
        this.file = this.file.replace(fileName + "####","");
        System.out.println(this.file);
    }

    public void writeFile() throws IOException {
        FileWithByte fwb = new FileWithByte();
        String filePath = System.getProperty("user.dir") + STATIC_RESOURCE + "/" + this.getUserid().toString();
        System.out.println("writeFile=====>" + filePath);
        for(Map.Entry<String,MultipartFile> entry : this.fileMap.entrySet() ){
            String fileName = entry.getKey();
            System.out.println("writeFile=====>" + fileName);
            System.out.println("writeFile=====>" + this.fileMap.get(fileName).getBytes().toString());
            fwb.getFile(this.fileMap.get(fileName).getBytes(),filePath,fileName);
        }
        System.out.println("writeFile=====>" + this.file);
    }



    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return roleid
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取模块名称
     *
     * @return moduleid - 模块名称
     */
    public String getModuleid() {
        return moduleid;
    }

    /**
     * 设置模块名称
     *
     * @param moduleid 模块名称
     */
    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * 获取类型
     *
     * @return typeid - 类型
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * 设置类型
     *
     * @param typeid 类型
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * 获取项目标准
     *
     * @return standardid - 项目标准
     */
    public Integer getStandardid() {
        return standardid;
    }

    /**
     * 设置项目标准
     *
     * @param standardid 项目标准
     */
    public void setStandardid(Integer standardid) {
        this.standardid = standardid;
    }

    /**
     * 获取学分
     *
     * @return creditid - 学分
     */
    public Integer getCreditid() {
        return creditid;
    }

    /**
     * 设置学分
     *
     * @param creditid 学分
     */
    public void setCreditid(Integer creditid) {
        this.creditid = creditid;
    }

    /**
     * 获取认定依据
     *
     * @return evidenceid - 认定依据
     */
    public Integer getEvidenceid() {
        return evidenceid;
    }

    /**
     * 设置认定依据
     *
     * @param evidenceid 认定依据
     */
    public void setEvidenceid(Integer evidenceid) {
        this.evidenceid = evidenceid;
    }

    /**
     * 获取证明所需的相关资料
     *
     * @return file - 证明所需的相关资料
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置证明所需的相关资料
     *
     * @param file 证明所需的相关资料
     */
    public void setFile(String file) {
        this.file = file;
    }
}