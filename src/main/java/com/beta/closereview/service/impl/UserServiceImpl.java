package com.beta.closereview.service.impl;

import com.beta.closereview.dao.Submission;
import com.beta.closereview.dao.User;
import com.beta.closereview.enums.StatusEnum;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.mapper.UserMapper;
import com.beta.closereview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer register(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer login(String email, String password) {
        User user = userMapper.selectByEmail(email);
        if (user == null){
            return StatusEnum.USER_NOT_FOUND.getCode();
        }

        return user.getPassword().equals(password) ?
                StatusEnum.OK.getCode() : StatusEnum.EMAIL_OR_PASSWORD_INCORRECT.getCode();
    }

    @Override
    public void logout(Integer uid) {

    }

    @Override
    public User getUserDetail(String email){
        return userMapper.selectByEmail(email);
    }
}
