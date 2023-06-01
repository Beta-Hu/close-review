package com.beta.closereview.service.impl;

import com.beta.closereview.dao.Submission;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    private SubmissionMapper submissionMapper;

    @Override
    public List<Submission> getSubmissions(Integer uid) {
        return submissionMapper.getSubmissionsOfUid(uid);
    }

    @Override
    public Integer addSubmission(Submission submission) {
        return submissionMapper.insertSelective(submission);
    }
}
