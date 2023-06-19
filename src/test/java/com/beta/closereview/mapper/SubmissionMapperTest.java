package com.beta.closereview.mapper;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.dao.UserDao;
import com.beta.closereview.dao.CommentDao;
import com.beta.closereview.pojo.Submission;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SubmissionMapperTest extends SuperCloseReviewTest {
    @Resource
    private SubmissionMapper submissionMapper;
    @Resource
    private ConferenceMapper conferenceMapper;
    @Resource
    private CommentDao commentDao;
    @Resource
    private UserDao userDao;

    @Test
    void listAcceptedSubmissions() {
        List<SimplifiedSubmissionVo> simplifiedSubmissionVos = submissionMapper.listAcceptedSubmissions(4);
        for (SimplifiedSubmissionVo s: simplifiedSubmissionVos)
            System.out.println(s);
    }

    @Test
    public void listSubmissions(){
        Set<Integer> submissionIds = userDao.getSubmissionOfAuthor(1);
        List<SimplifiedSubmissionVo> submissionVos =
                submissionMapper.listSubmissionBySids(-1, new ArrayList<>(submissionIds));
        for (SimplifiedSubmissionVo s: submissionVos)
            System.out.println(s);
    }

    @Test
//    @Transactional
    public void addSubmissions(){
        List<Integer> authorsIds;
        Submission submission;
        String title;
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
                    for (int i = 0; i < (3 * Math.random() + 2); i++) {
                        if (Math.random() > 0.1)
                            authorsIds.add((int) (500 * Math.random() + 1));
                        else
                            authorsIds.add(1);
                    }
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

                    userDao.addAuthor(submissionId, authorsIds);

                    submissionId ++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}