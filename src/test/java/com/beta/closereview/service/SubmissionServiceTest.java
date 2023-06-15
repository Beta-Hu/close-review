package com.beta.closereview.service;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import com.beta.closereview.vo.SubmissionVo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionServiceTest extends SuperCloseReviewTest {
    @Resource
    private SubmissionService submissionService;
    @Resource
    private RedisTemplate<Integer, Integer> redisTemplate;

    @Test
    void listSubmission() {
        List<SimplifiedSubmissionVo> submissionVos = submissionService.listAcceptedSubmission(4);
        for (SimplifiedSubmissionVo s: submissionVos)
            System.out.println(s);
    }

    @Test
    public void showSubmissionDetail(){
        SubmissionVo submissionVo = submissionService.showSubmissionDetail(2);
        System.out.println(submissionVo);
    }
}
