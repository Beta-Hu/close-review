package com.beta.closereview.mapper;

import com.beta.closereview.dao.Conference;

public interface ConferenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Conference record);

    int insertSelective(Conference record);

    Conference selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Conference record);

    int updateByPrimaryKey(Conference record);
}