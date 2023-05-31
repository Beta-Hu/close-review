package com.beta.closereview.dao;

public class Area {
    private String name;

    private Integer conferenceId;

    private Integer areaChair;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Integer getAreaChair() {
        return areaChair;
    }

    public void setAreaChair(Integer areaChair) {
        this.areaChair = areaChair;
    }
}