package com.beta.closereview.mapper;

import com.beta.closereview.dao.Submission;

import java.util.List;

public interface SubmissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Submission record);

    int insertSelective(Submission record);

    Submission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Submission record);

    int updateByPrimaryKeyWithBLOBs(Submission record);

    int updateByPrimaryKey(Submission record);

    List<Submission> getSubmissionsOfUid(Integer uid);
}