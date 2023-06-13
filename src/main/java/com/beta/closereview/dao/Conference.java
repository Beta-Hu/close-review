package com.beta.closereview.dao;

import lombok.Data;

@Data
public class Conference {
    private Integer id;

    private String name;

    private String acronym;

    private Integer year;

    private Integer status;
}