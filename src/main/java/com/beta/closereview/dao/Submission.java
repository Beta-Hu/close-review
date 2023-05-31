package com.beta.closereview.dao;

import java.util.Date;

public class Submission {
    private Integer id;

    private String title;

    private Integer corresponding;

    private Integer conference;

    private Integer mainArea;

    private String file;

    private String supportMaterial;

    private Integer status;

    private Date submitTime;

    private Date updateTime;

    private String author;

    private String secondArea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCorresponding() {
        return corresponding;
    }

    public void setCorresponding(Integer corresponding) {
        this.corresponding = corresponding;
    }

    public Integer getConference() {
        return conference;
    }

    public void setConference(Integer conference) {
        this.conference = conference;
    }

    public Integer getMainArea() {
        return mainArea;
    }

    public void setMainArea(Integer mainArea) {
        this.mainArea = mainArea;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public String getSupportMaterial() {
        return supportMaterial;
    }

    public void setSupportMaterial(String supportMaterial) {
        this.supportMaterial = supportMaterial == null ? null : supportMaterial.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getSecondArea() {
        return secondArea;
    }

    public void setSecondArea(String secondArea) {
        this.secondArea = secondArea == null ? null : secondArea.trim();
    }
}