package com.example.phq.pojo;

import java.io.Serializable;

public class PhqSchool implements Serializable {
    private Integer id;

    private String schoolName;

    private Integer atCreate;

    private Integer atUpdate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Integer getAtCreate() {
        return atCreate;
    }

    public void setAtCreate(Integer atCreate) {
        this.atCreate = atCreate;
    }

    public Integer getAtUpdate() {
        return atUpdate;
    }

    public void setAtUpdate(Integer atUpdate) {
        this.atUpdate = atUpdate;
    }
}