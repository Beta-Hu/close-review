package com.beta.closereview.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String organization;

    public User(String username, String password, String email, String organization) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.organization = organization;
    }

    public User() {
    }
}