package com.example.phq.pojo;

import java.io.Serializable;

public class PhqQuestion implements Serializable {
    private Integer id;

    private Integer quesTplId;

    private String question;

    private Integer atCreate;

    private Integer atUpdate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuesTplId() {
        return quesTplId;
    }

    public void setQuesTplId(Integer quesTplId) {
        this.quesTplId = quesTplId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
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