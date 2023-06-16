package com.beta.closereview.controller;

import com.beta.closereview.pojo.Conference;
import com.beta.closereview.pojo.User;
import com.beta.closereview.service.ConferenceService;
import com.beta.closereview.service.SubmissionService;
import com.beta.closereview.vo.ResponseVo;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conference")
public class ConferenceController {
    @Resource
    private ConferenceService conferenceService;
    @Resource
    private SubmissionService submissionService;

    /**
     * 列出全部会议
     * @return
     */
    @GetMapping("/list")
    public ResponseVo<List<Conference>> listConference(){
        List<Conference> conferences = conferenceService.listConference();
        return new ResponseVo<>(conferences);
    }

    /**
     * 查看非活动状态的会议接收的所有submission的基本信息
     * @param conferenceId 会议id
     * @return 该会议接收的所有submission基本信息构成的数组
     */
    @GetMapping("/inactive/{conferenceId}")
    public ResponseVo<List<SimplifiedSubmissionVo>> listAccepted(@PathVariable("conferenceId") Integer conferenceId){
        List<SimplifiedSubmissionVo> submissionVos = submissionService.listAcceptedSubmission(conferenceId);
        return new ResponseVo<>(submissionVos);
    }

    /**
     * 查看登录用户在活动状态会议所有提交的submission的基本信息
     * @param conferenceId 会议id
     * @return 登录用户在该会议提交的所有submission基本信息构成的列表
     */
    @GetMapping("/active/{conferenceId}")
    public ResponseVo<List<SimplifiedSubmissionVo>> listSubmission(@PathVariable("conferenceId") Integer conferenceId,
                                                                   HttpSession session){
        User user = (User) session.getAttribute("user");
        List<SimplifiedSubmissionVo> submissionVos = submissionService.listSubmissions(conferenceId, user.getId());
        return new ResponseVo<>(submissionVos);
    }
}
