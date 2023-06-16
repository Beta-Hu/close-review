package com.beta.closereview.service;

import com.beta.closereview.mapper.UserMapper;
import com.beta.closereview.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public boolean verifyUser(String email, String password){
        return password.equals(userMapper.getPassword(email));
    }

    public User getUser(String email){
        User user = userMapper.getUserByEmail(email);
        if (user != null)
            user.setPassword(null);
        return user;
    }

    public Integer addUser(User user){
        return userMapper.insertSelective(user);
    }
}
