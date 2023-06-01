package com.beta.closereview.service;

import com.beta.closereview.dao.Submission;
import com.beta.closereview.dao.User;

import java.util.List;

public interface UserService {
    void register(User user);

    Integer login(String email, String password);

    void logout(Integer uid);

    User getUserDetail(String email);

    List<Submission> getSubmissions(Integer uid);
}
