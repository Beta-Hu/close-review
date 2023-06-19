package com.beta.closereview.mapper;

import com.beta.closereview.pojo.Submission;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SubmissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Submission record);

    int insertSelective(Submission record);

    Submission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Submission record);

    int updateByPrimaryKey(Submission record);

    List<SimplifiedSubmissionVo> listAcceptedSubmissions(Integer conferenceId);

    List<SimplifiedSubmissionVo> listSubmissionBySids(@Param("conferenceId") Integer conferenceId,
                                                      @Param("submissionIds") List<Integer> submissionIds);
    List<Submission> listPublicationBySids(@Param("submissionIds") List<Integer> submissionIds);

    int updateSubmissionStatus(@Param("targetStatus") Integer targetStatus,
                               @Param("conferenceId") Integer conferenceId);

    int updateDecisionStatus(Map<Integer, Integer> status);
}