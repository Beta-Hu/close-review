package com.beta.closereview.mapper;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.dao.CommentDao;
import com.beta.closereview.pojo.Conference;
import com.beta.closereview.pojo.Submission;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import com.beta.closereview.vo.SubmissionVo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionMapperTest extends SuperCloseReviewTest {
    @Resource
    private SubmissionMapper submissionMapper;
    @Resource
    private ConferenceMapper conferenceMapper;
    @Resource
    private CommentDao commentDao;

    @Test
    void listAcceptedSubmissions() {
        List<SimplifiedSubmissionVo> simplifiedSubmissionVos = submissionMapper.listAcceptedSubmissions(4);
        for (SimplifiedSubmissionVo s: simplifiedSubmissionVos)
            System.out.println(s);
    }

    @Test
//    @Transactional
    public void addSubmissions(){
        Integer conferenceId;
        List<Integer> authorsIds;
        List<String> comments;
        Set<Integer> reviewerIds;
        Submission submission;
        String title;
        Integer status;
        Integer submissionId = 1;

        try(FileReader reader = new FileReader("E:/aa.txt");
            BufferedReader br = new BufferedReader(reader)){
            String line;
            while((line = br.readLine()) != null){
                if (line.length() > 0) {
                    String[] sentence = line.split("[,|.]");
                    title = sentence[0];
                    for (String s: sentence){
                        if (s.split(" ").length > 6 && s.split(" ").length < 15){
                            title = s;
                            break;
                        }
                    }
                    title = title.replaceAll("^[ ]+", "");
                    title = title.substring(0, 1).toUpperCase() + title.substring(1);

                    authorsIds = new ArrayList<>();
                    for (int i = 0; i < (2 * Math.random() + 2); i++)
                        authorsIds.add((int) (500 * Math.random() + 1));
                    authorsIds = new ArrayList<>(new HashSet<>(authorsIds));

                    submission = new Submission();

                    submission.setId(submissionId);
                    submission.setAbstracts(line);
                    submission.setFile("aaa.pdf");
                    submission.setSupportMaterial("bbb.pdf");
                    submission.setConference((int) (7 * Math.random() + 1));
                    submission.setCorresponding(authorsIds.get((int) (authorsIds.size() * Math.random())));
                    submission.setTitle(title);
                    submission.setStatus(0);

                    submissionMapper.insertSelective(submission);

                    commentDao.addAuthor(submissionId, authorsIds);

                    submissionId ++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}