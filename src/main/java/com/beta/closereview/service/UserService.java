package com.beta.closereview.service;

import com.beta.closereview.dao.User;

public interface UserService {
    void register(User user);

    Integer login(String email, String password);

    void logout(Integer uid);
}
