package com.beta.closereview.dao;

import lombok.Data;

@Data
public class Conference {
    private Integer id;

    private String name;

    private String email;

    private Integer year;

    private String chair;
}