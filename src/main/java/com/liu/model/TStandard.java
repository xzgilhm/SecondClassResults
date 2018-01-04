package com.liu.model;

import javax.persistence.*;

@Table(name = "t_standard")
public class TStandard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目标准
     */
    private String title;

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
     * 获取项目标准
     *
     * @return title - 项目标准
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置项目标准
     *
     * @param title 项目标准
     */
    public void setTitle(String title) {
        this.title = title;
    }
}