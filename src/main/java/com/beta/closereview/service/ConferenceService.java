package com.beta.closereview.service;

import com.beta.closereview.mapper.ConferenceMapper;
import com.beta.closereview.pojo.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {
    @Autowired
    private ConferenceMapper conferenceMapper;

    public List<Conference> listConference(){
        return conferenceMapper.listAllConference();
    }
}
