package com.beta.closereview.dao;

import lombok.Data;

import java.util.Date;

@Data
public class Submission {
    private Integer id;

    private String title;

    private String abstracts;

    private Integer corresponding;

    private Integer conference;

    private String file;

    private String supportMaterial;

    private Integer status;

    private Date submitTime;

    private Date updateTime;
}