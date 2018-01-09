package com.liu.model;

import com.liu.core.FileWithByte;

import javax.persistence.*;

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
    private byte[] file;

    @Transient
    private String fileName;

    public String getFileName() {
        FileWithByte fwb = new FileWithByte();
        String path = System.getProperty("user.dir")+"/src/main/resources/static/images";
        String name = this.getUserid()+this.getModuleid()+this.getTypeid()+".jpg";
        fwb.getFile(this.getFile(),path,name);
        return name;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
    public byte[] getFile() {
        return file;
    }

    /**
     * 设置证明所需的相关资料
     *
     * @param file 证明所需的相关资料
     */
    public void setFile(byte[] file) {
        this.file = file;
    }
}