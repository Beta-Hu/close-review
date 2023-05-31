package com.beta.closereview.dao;

public class Conference {
    private Integer id;

    private String name;

    private String email;

    private Integer year;

    private String chair;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair == null ? null : chair.trim();
    }
}