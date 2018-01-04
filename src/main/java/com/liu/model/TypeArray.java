package com.liu.model;

import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
public class TypeArray {
    private String typeId;
    private String typeName =" xx ";
    private List<StandardArray> standardArrays;

    public List<StandardArray> getStandardArrays() {
        return standardArrays;
    }

    public void setStandardArrays(List<StandardArray> standardArrays) {
        this.standardArrays = standardArrays;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "TypeArray{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
