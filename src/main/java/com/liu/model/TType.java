package com.liu.model;

import javax.persistence.*;

@Table(name = "t_type")
public class TType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 类别名称
     */
    private String typename;

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
     * 获取类别名称
     *
     * @return typename - 类别名称
     */
    public String getTypename() {
        return typename;
    }

    /**
     * 设置类别名称
     *
     * @param typename 类别名称
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }
}