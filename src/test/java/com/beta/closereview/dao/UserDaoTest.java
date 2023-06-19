package com.beta.closereview.dao;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.pojo.Submission;
import com.beta.closereview.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest extends SuperCloseReviewTest {
    @Resource
    private UserDao userDao;
    @Resource
    private SubmissionMapper submissionMapper;

    @Test
    void getAuthors() {
    }

    @Test
    void getAuthor() {
    }

    @Test
    void getSubmissionOfAuthor() {
    }

    @Test
    void getReviewOfReviewer() {
    }

    @Test
    void addReviewer() {
        Submission submission;
        List<Integer> reviewList;
        Set<Integer> authors;
        Integer reviewer;
        for (int i = 1; i < 86; i++){
            reviewList = new ArrayList<>();
            submission = submissionMapper.selectByPrimaryKey(i);
            authors = userDao.getAuthor(submission.getId());
            if (submission.getStatus() > 0){
                for (int k = 0; k < 2 * Math.random() + 2; k++) {
                    if (Math.random() > 0.1)
                        reviewer = (int) (500 * Math.random() + 1);
                    else
                        reviewer = 1;
                    if (! authors.contains(reviewer))
                        reviewList.add(reviewer);
                    else
                        k --;
                }
                userDao.addReviewer(submission.getId(), reviewList);
            }
        }
    }

    @Test
    void addAuthor() {
    }
}
