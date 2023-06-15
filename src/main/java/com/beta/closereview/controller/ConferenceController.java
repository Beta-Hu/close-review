package com.beta.closereview.controller;

import com.beta.closereview.pojo.Conference;
import com.beta.closereview.service.ConferenceService;
import com.beta.closereview.service.SubmissionService;
import com.beta.closereview.vo.ResponseVo;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public ResponseVo<List<Conference>> listConference(){
        List<Conference> conferences = conferenceService.listConference();
        return new ResponseVo<>(conferences);
    }

    @GetMapping("/{conferenceId}")
    public ResponseVo<List<SimplifiedSubmissionVo>> listSubmission(@PathVariable("conferenceId") Integer conferenceId){
        List<SimplifiedSubmissionVo> submissionVos = submissionService.listAcceptedSubmission(conferenceId);
        return new ResponseVo<>(submissionVos);
    }
}
