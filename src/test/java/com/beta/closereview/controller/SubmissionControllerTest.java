package com.beta.closereview.controller;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.pojo.User;
import com.beta.closereview.vo.ResponseVo;
import com.beta.closereview.vo.SubmissionVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.session.StandardManager;
import org.apache.catalina.session.StandardSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionControllerTest extends SuperCloseReviewTest {
    @Resource
    private SubmissionController submissionController;

    @Test
    void showAcceptedSubmissionDetail() {
    }

    @Test
    void showSubmittingSubmissionDetail() {
    }
}