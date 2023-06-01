package com.beta.closereview.service;

import com.beta.closereview.dao.Submission;

import java.util.List;

public interface SubmissionService {
    List<Submission> getSubmissions(Integer uid);
}
