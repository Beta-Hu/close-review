package com.beta.closereview.service;

import com.beta.closereview.mapper.ConferenceMapper;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.pojo.Conference;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConferenceService {
    @Resource
    private ConferenceMapper conferenceMapper;
    @Resource
    private SubmissionMapper submissionMapper;

    /**
     * 列出所有会议(任意状态的)
     * @return 会议列表
     */
    public List<Conference> listConference(){
        return conferenceMapper.listAllConference();
    }

    private void generateReviewRelationship(){
        List<Conference> conferences = conferenceMapper.listConferenceOfStatus(0);

        for (Conference c: conferences){
            if (c.getReviewDue().compareTo(new Date(System.currentTimeMillis())) > 0) {
                submissionMapper.updateSubmissionStatus(2, c.getId());
                continue;
            }
            if (c.getSubmitDue().compareTo(new Date(System.currentTimeMillis())) > 0) {
                submissionMapper.updateSubmissionStatus(1, c.getId());
                continue;
            }
            if (c.getRebuttalDue().compareTo(new Date(System.currentTimeMillis())) > 0){
                // 依据review的评分排序，并给出最终结果；
            }
        }
    }
}
