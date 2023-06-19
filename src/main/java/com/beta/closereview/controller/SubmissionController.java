package com.beta.closereview.controller;

import com.beta.closereview.form.CommentForm;
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

    /**
     * 查看已被接收的submission的详情
     * @param submissionId submission的id
     * @return submission的详细信息
     */
    @GetMapping("/accepted/{submissionId}")
    public ResponseVo<SubmissionVo> showAcceptedSubmissionDetail(@PathVariable("submissionId") Integer submissionId){
        SubmissionVo submissionVo = submissionService.showSubmissionDetail(submissionId);
        return new ResponseVo<>(submissionVo);
    }

    /**
     * 用户在conference-active.html页面点击查看其提交的submission的详情(加载submission.html时获取上次提交的内容)
     * @param submissionId submission的id
     * @param session
     * @return 用户上次提交的submission的详情
     */
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

    /**
     * 用户创建或更新一个submission
     * @param form 来自前端的submission表单
     * @param session
     * @return 更新后的submission详情
     */
    @PostMapping("/submitting/{submissionId}")
    public ResponseVo<SubmissionVo> addSubmittingSubmissionDetail(SubmissionForm form,
                                                                  HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            submissionService.updateSubmission(form, user);
            return new ResponseVo<>();
        }
        return new ResponseVo<>(0x300002, "update submission failed", null);
    }

    /**
     * reviewer加载submission时获取上一次review的comments
     * @param submissionId
     * @param session
     * @return
     */
    @GetMapping("/review/{submissionId}")
    public ResponseVo<SubmissionVo> showCurrentReview(@PathVariable Integer submissionId,
                                                      HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            SubmissionVo submissionVo = submissionService.getCommentsOfReviewer(submissionId, user.getId());
            return new ResponseVo<>(submissionVo);
        }
        return new ResponseVo<>(0x300003, "permission died", null);
    }

    /**
     * 用户创建或更新一个submission
     * @param form 来自前端的submission表单
     * @param session
     * @return 更新后的submission详情
     */
    @PostMapping("/review/{submissionId}")
    public ResponseVo<SubmissionVo> addComments(CommentForm form,
                                                HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (submissionService.addCommentsOfReviewer(user.getId(), form))
                return new ResponseVo<>();
            else
                return new ResponseVo<>(0x300005, "permission died", null);
        }
        return new ResponseVo<>(0x300004, "permission died, login first", null);
    }
}
