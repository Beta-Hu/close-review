package com.beta.closereview.mapper;

import com.beta.closereview.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String getPassword(String email);

    User getUserByEmail(String email);

    List<User> listUsersByIds(List<Integer> ids);

    List<User> listUsersByEmails(List<String> emails);
}