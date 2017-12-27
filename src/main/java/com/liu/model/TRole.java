package com.liu.model;

import javax.persistence.*;

@Table(name = "t_role")
public class TRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名（认定单位）
     */
    private String unitname;

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
     * 获取角色名（认定单位）
     *
     * @return unitname - 角色名（认定单位）
     */
    public String getUnitname() {
        return unitname;
    }

    /**
     * 设置角色名（认定单位）
     *
     * @param unitname 角色名（认定单位）
     */
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
}