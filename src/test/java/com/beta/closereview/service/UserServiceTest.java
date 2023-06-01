package com.beta.closereview.service;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends SuperCloseReviewTest {
    @Autowired
    private UserMapper userMapper;
}