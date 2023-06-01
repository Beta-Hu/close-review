package com.beta.closereview.service;

import com.beta.closereview.dao.User;

public interface UserService {
    Integer login(String email, String password);

    void logout(Integer uid);

    User getUserDetail(String email);

    Integer register(User user);
}
