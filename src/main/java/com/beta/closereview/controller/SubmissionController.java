package com.beta.closereview.controller;

import com.beta.closereview.form.SubmissionForm;
import com.beta.closereview.pojo.User;
import com.beta.closereview.service.SubmissionService;
import com.beta.closereview.vo.ResponseVo;
import com.beta.closereview.vo.SubmissionVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
    @Resource
    private SubmissionService submissionService;

    @GetMapping("/accepted/{submissionId}")
    public ResponseVo<SubmissionVo> showAcceptedSubmissionDetail(@PathVariable("submissionId") Integer submissionId){
        SubmissionVo submissionVo = submissionService.showSubmissionDetail(submissionId);
        return new ResponseVo<>(submissionVo);
    }

    @GetMapping("/submitting/{submissionId}")
    public ResponseVo<SubmissionVo> showSubmittingSubmissionDetail(@PathVariable("submissionId") Integer submissionId,
                                                                   HttpSession session){
        User user = (User) session.getAttribute("user");
        SubmissionVo submissionVo = null;
        if (user != null)
            submissionVo = submissionService.showSubmissionDetail(submissionId, user.getId());

        if (submissionVo != null)
            return new ResponseVo<>(submissionVo);
        else
            return new ResponseVo<>(0x300001, "permission died", null);
    }

    @PostMapping("/submitting/{submissionId}")
    public ResponseVo<SubmissionVo> addSubmittingSubmissionDetail(SubmissionForm form,
                                                                  @PathVariable String submissionId,
                                                                  HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            submissionService.updateSubmission(form, user);
            return new ResponseVo<>();
        }
        return new ResponseVo<>(0x300002, "update submission failed", null);
    }
}
