package com.beta.closereview.mapper;

import com.beta.closereview.dao.Submission;

public interface SubmissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Submission record);

    int insertSelective(Submission record);

    Submission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Submission record);

    int updateByPrimaryKey(Submission record);
}