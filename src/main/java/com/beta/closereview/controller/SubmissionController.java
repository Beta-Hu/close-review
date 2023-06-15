package com.beta.closereview.controller;

import com.beta.closereview.pojo.Comment;
import com.beta.closereview.service.SubmissionService;
import com.beta.closereview.vo.ResponseVo;
import com.beta.closereview.vo.SubmissionVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    @Resource
    private SubmissionService submissionService;

    @GetMapping("/{submissionId}")
    public ResponseVo<SubmissionVo> showSubmissionDetail(@PathVariable("submissionId") Integer submissionId){
        return null;
    }
}
