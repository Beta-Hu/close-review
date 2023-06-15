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

    List<User> getUsersByIds(List<Integer> ids);

    List<User> getUsersByEmails(List<String> emails);
}