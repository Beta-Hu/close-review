package com.beta.closereview.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Conference {
    private Integer id;

    private String name;

    private String acronym;

    private Integer year;

    private Integer status;

    private Date submitDue;

    private Date reviewDue;

    private Date rebuttalDue;
}