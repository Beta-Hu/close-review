package com.beta.closereview.service;

import com.beta.closereview.mapper.UserMapper;
import com.beta.closereview.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 用户鉴定
     * @param email 邮箱
     * @param password 密码
     * @return 鉴定结果
     */
    public boolean verifyUser(String email, String password, String verifyCode) {
        String correctPassword = userMapper.getPassword(email) + verifyCode;
        String md5Password = DigestUtils.md5DigestAsHex((correctPassword).getBytes());
        return password.equals(md5Password);
    }

    /**
     * 依据email查找用户
     * @param email 邮箱
     * @return 用户
     */
    public User getUser(String email){
        User user = userMapper.getUserByEmail(email);
        if (user != null)
            user.setPassword(null);
        return user;
    }

    /**
     * 添加用户
     * @param user 用户
     * @return 添加是否成功
     */
    public Integer addUser(User user){
        return userMapper.insertSelective(user);
    }
}
