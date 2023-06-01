package com.beta.closereview.dao;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Integer organization;
}