package com.beta.closereview.service;

import com.beta.closereview.mapper.ConferenceMapper;
import com.beta.closereview.pojo.Conference;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {
    @Resource
    private ConferenceMapper conferenceMapper;

    /**
     * 列出所有会议(任意状态的)
     * @return 会议列表
     */
    public List<Conference> listConference(){
        return conferenceMapper.listAllConference();
    }
}
