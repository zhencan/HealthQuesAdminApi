package com.example.phq.pojo;

import java.io.Serializable;

public class PhqHealthyMessage implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer quesTplId;

    private String quesOption;

    private Integer atCreate;

    private Integer atUpdate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuesTplId() {
        return quesTplId;
    }

    public void setQuesTplId(Integer quesTplId) {
        this.quesTplId = quesTplId;
    }

    public String getQuesOption() {
        return quesOption;
    }

    public void setQuesOption(String quesOption) {
        this.quesOption = quesOption == null ? null : quesOption.trim();
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