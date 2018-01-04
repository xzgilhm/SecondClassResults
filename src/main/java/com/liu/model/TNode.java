package com.liu.model;

import javax.persistence.*;

@Table(name = "t_node")
public class TNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String moduleid;

    private String typeid;

    private Integer standardid;

    private Integer creditid;

    private Integer evidenceid;

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
     * @return moduleid
     */
    public String getModuleid() {
        return moduleid;
    }

    /**
     * @param moduleid
     */
    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    /**
     * @return typeid
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * @param typeid
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * @return standardid
     */
    public Integer getStandardid() {
        return standardid;
    }

    /**
     * @param standardid
     */
    public void setStandardid(Integer standardid) {
        this.standardid = standardid;
    }

    /**
     * @return creditid
     */
    public Integer getCreditid() {
        return creditid;
    }

    /**
     * @param creditid
     */
    public void setCreditid(Integer creditid) {
        this.creditid = creditid;
    }

    /**
     * @return evidenceid
     */
    public Integer getEvidenceid() {
        return evidenceid;
    }

    /**
     * @param evidenceid
     */
    public void setEvidenceid(Integer evidenceid) {
        this.evidenceid = evidenceid;
    }
}