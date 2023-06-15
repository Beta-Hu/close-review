package com.beta.closereview.dao;

import com.beta.closereview.SuperCloseReviewTest;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommentDaoTest extends SuperCloseReviewTest {
    @Resource
    private CommentDao commentDao;

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
        List<List<String>> commentsList = commentDao.getComment(15);

        for (List<String> comments: commentsList){
            for (String comment: comments)
                System.out.println(comment);
            System.out.println();
        }
    }

    @Test
    void addAuthor() {
        List<Integer> authors = new ArrayList<>();
        authors.add(78);
        authors.add(48);
        authors.add(46);
        commentDao.addAuthor(15, authors);
    }

    @Test
    void addComment() {
        List<String> comments = new ArrayList<>();
        comments.add(String.valueOf(7));
        comments.add("Started CommentDaoTest in 2.191 seconds (process running for 3.033)");
        comments.add("Finished Spring Data repository scanning in 17 ms. Found 0 Redis repository interfaces.");
        comments.add("No active profile set, falling back to 1 default profile: 'default'");
        commentDao.addComment(15, comments);
    }
}