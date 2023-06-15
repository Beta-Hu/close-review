package com.beta.closereview.mapper;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.pojo.Conference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConferenceMapperTest extends SuperCloseReviewTest {
    @Autowired
    private ConferenceMapper conferenceMapper;

    @Test
    void listAllConference() {
        List<Conference> conferences = conferenceMapper.listAllConference();
        for (Conference c: conferences)
            System.out.println(c);
    }
}