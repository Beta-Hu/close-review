package com.beta.closereview.service;

import com.beta.closereview.SuperCloseReviewTest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConferenceServiceTest extends SuperCloseReviewTest {
    @Resource
    private ConferenceService conferenceService;

    @Test
    void listConference() {
    }

    @Test
    public void updateConferenceStatus(){
        conferenceService.updateConferenceStatus();
    }
}