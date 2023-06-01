package com.beta.closereview.mapper;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.dao.Submission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class SubmissionMapperTest extends SuperCloseReviewTest {
    @Autowired
    private SubmissionMapper submissionMapper;

    @Test
    void getSubmissionsOfUid() {
        List<Submission> submissions = submissionMapper.getSubmissionsOfUid(100);
    }
}
