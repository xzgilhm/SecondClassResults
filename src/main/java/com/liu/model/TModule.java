package com.liu.model;

import javax.persistence.*;

@Table(name = "t_module")
public class TModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 模块名称
     */
    private String modulename;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取模块名称
     *
     * @return modulename - 模块名称
     */
    public String getModulename() {
        return modulename;
    }

    /**
     * 设置模块名称
     *
     * @param modulename 模块名称
     */
    public void setModulename(String modulename) {
        this.modulename = modulename;
    }
}