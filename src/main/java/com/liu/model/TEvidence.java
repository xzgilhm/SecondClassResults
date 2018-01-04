package com.liu.model;

import javax.persistence.*;

@Table(name = "t_evidence")
public class TEvidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 认定依据
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
     * 获取认定依据
     *
     * @return title - 认定依据
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置认定依据
     *
     * @param title 认定依据
     */
    public void setTitle(String title) {
        this.title = title;
    }
}