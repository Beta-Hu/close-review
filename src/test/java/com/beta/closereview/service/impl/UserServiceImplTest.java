package com.beta.closereview.service.impl;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.dao.Submission;
import com.beta.closereview.service.SubmissionService;
import com.beta.closereview.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends SuperCloseReviewTest {
    @Autowired
    private UserService userService;
    @Autowired
    private SubmissionService submissionService;

    @Test
    void register() {
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

    @Test
    void getUserDetail() {
    }

    @Test
    void getSubmissions() {
        List<Submission> submissions = submissionService.getSubmissions(100);
        for (Submission submission : submissions) {
            System.out.println(submission);
        }
    }
}