package com.liu.model;

import com.liu.core.FileWithByte;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

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
    private byte[] fileByte;

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    /**
     * 把files的路径存入数据库,把files写到 路径下
     * @param files
     */
//    @Transient
//    String NAMEINSQL = "";

    public void setFileByte(List<MultipartFile> files,String fileNameTop,int userId) throws IOException {
        FileWithByte fwb = new FileWithByte();
        for(int i=0;i<files.size();i++) {
            String filePath = System.getProperty("user.dir") + STATIC_RESOURCE + "/" + userId;
            String fileName = fileNameTop +"&&" + files.get(i).getOriginalFilename() ;
            fwb.getFile(files.get(i).getBytes(),filePath,fileName);
            this.file += fileName + "####";
        }
        System.out.println("name in sql");
        System.out.println(this.file);
//        this.file = NAMEINSQL;
    }

    public void deleteName(String mark,String fileName){
        this.file = this.file.replace(mark + "&&"+ fileName + "####","");
        System.out.println("deleteName():  name in sql");
        System.out.println(this.file);
//        this.file = NAMEINSQL;
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