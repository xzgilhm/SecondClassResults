package com.liu.model;

import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
public class TypeArray {
    private String typeId;
    private String typeName;
    private Integer evidenceid;
    private String evidenceTitle;

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

    public Integer getEvidenceid() {
        return evidenceid;
    }

    public void setEvidenceid(Integer evidenceid) {
        this.evidenceid = evidenceid;
    }

    public String getEvidenceTitle() {
        return evidenceTitle;
    }

    public void setEvidenceTitle(String evidenceTitle) {
        this.evidenceTitle = evidenceTitle;
    }

    @Override
    public String toString() {
        return "TypeArray{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
