package com.beta.closereview.dao;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.mapper.ConferenceMapper;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.pojo.Comment;
import com.beta.closereview.pojo.Submission;
import com.beta.closereview.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class CommentDaoTest extends SuperCloseReviewTest {
    @Resource
    private CommentDao commentDao;
    @Resource
    private UserDao userDao;
    @Resource
    private SubmissionMapper submissionMapper;
    @Resource
    private ConferenceMapper conferenceMapper;

    @Test
    void getAuthors() {
    }

    @Test
    void getAuthor() {
    }

    @Test
    void getComments() {
    }

    @Test
    void getComment() {
        List<Comment> commentsList = commentDao.getComments(1);
        for (Comment comment: commentsList){
            System.out.println(comment);
        }
    }

    @Test
    void addAuthor() {
        List<Integer> authors = new ArrayList<>();
        authors.add(78);
        authors.add(48);
        authors.add(46);
        userDao.addAuthor(15, authors);
    }

    @Test
    void addComment() {
        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setInitialComments(new ArrayList<>());
        comment.getInitialComments().add("Started CommentDaoTest in 2.191 seconds (process running for 3.033)");
        comment.getInitialComments().add("Finished Spring Data repository scanning in 17 ms. Found 0 Redis repository interfaces.");
        comment.getInitialComments().add("No active profile set, falling back to 1 default profile: 'default'");
        comment.setInitialScore(3);
        comment.setReviewer(448);
        comments.add(comment);

        comment = new Comment();
        comment.setInitialComments(new ArrayList<>());
        comment.getInitialComments().add("SqlSession was not registered for synchronization because synchronization is not active");
        comment.getInitialComments().add("Added connection com.mysql.cj.jdbc.ConnectionImpl@107b2e56");
        comment.getInitialComments().add("An older version [1.2.36] of the Apache Tomcat Native library is installed");
        comment.setInitialScore(4);
        comment.setReviewer(90);
        comments.add(comment);

        comment = new Comment();
        comment.setInitialComments(new ArrayList<>());
        comment.getInitialComments().add("\"Medicine\" refers to the practice of non-operative medicine");
        comment.getInitialComments().add("some specialties of medicine do not fit easily into either of these categories");
        comment.setInitialScore(2);
        comment.setReviewer(407);
        comments.add(comment);

        comment = new Comment();
        comment.setInitialComments(new ArrayList<>());
        comment.getInitialComments().add("class annotated with @DirtiesContext [false] with mode [null]");
        comment.getInitialComments().add("DEBUG org.springframework.test.context.support.AbstractDirtiesContextTestExecutionListener");
        comment.setInitialScore(4);
        comment.setReviewer(305);
        comments.add(comment);

        commentDao.addComment(7, comments);
    }

    @Test
    void getAllCommentsBySubmissionIds() {
        List<Integer> ids = submissionMapper.listSubmissionIdsOfConference(6);

        Map<Object, Object> finalScores = commentDao.getAllCommentsBySubmissionIds(ids);

        for (Map.Entry<Object, Object> m: finalScores.entrySet())
            System.out.println(m.getKey() + ": " + m.getValue());
    }
}
