package com.beta.closereview.service;

import com.beta.closereview.dao.CommentDao;
import com.beta.closereview.mapper.ConferenceMapper;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.pojo.Comment;
import com.beta.closereview.pojo.Conference;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ConferenceService {
    @Resource
    private ConferenceMapper conferenceMapper;
    @Resource
    private SubmissionMapper submissionMapper;
    @Resource
    private CommentDao commentDao;

    /**
     * 列出所有会议(任意状态的)
     * @return 会议列表
     */
    public List<Conference> listConference(){
        return conferenceMapper.listAllConference();
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void updateConferenceStatus(){
        log.warn("updating conference status");
        List<Conference> conferences = conferenceMapper.listConferenceOfStatus(Arrays.asList(0, 1, 2));

        for (Conference c: conferences){
            if (c.getRebuttalDue().compareTo(new Date(System.currentTimeMillis())) < 0){
                List<Integer> ids = submissionMapper.listSubmissionIdsOfConference(c.getId());
                Map<Object, Object> comments = commentDao.getAllCommentsBySubmissionIds(ids);
                List<List<Integer>> finalScore = new ArrayList<>();
                List<Integer> item;
                List<Integer> score;
                for (Map.Entry<Object, Object> m: comments.entrySet()) {
                    score = new ArrayList<>();
                    for (Comment comment : (List<Comment>) m.getValue()) {
                        if (comment.getFinalScore() != null)
                            score.add(comment.getFinalScore());
                    }
                    item = new ArrayList<>();
                    item.add((Integer) m.getKey());
                    item.add(score.stream().reduce(0, Integer::sum));

                    finalScore.add(item);
                }

                finalScore.sort(Comparator.comparingInt(o -> o.get(1)));

                Map<Integer, Integer> finalDecision = new HashMap<>();
                for (int i = 0; i < finalScore.size(); i++){
                    if (i < finalScore.size() / 5)
                        finalDecision.put(finalScore.get(i).get(0), 3);
                    else
                        finalDecision.put(finalScore.get(i).get(0), 3);
                }
                submissionMapper.updateDecisionStatus(finalDecision);

                c.setStatus(3);
                conferenceMapper.updateByPrimaryKey(c);
                continue;
            }
            if (c.getReviewDue().compareTo(new Date(System.currentTimeMillis())) < 0) {
                submissionMapper.updateSubmissionStatus(2, c.getId());
                c.setStatus(2);
                conferenceMapper.updateByPrimaryKey(c);
                continue;
            }
            if (c.getSubmitDue().compareTo(new Date(System.currentTimeMillis())) < 0) {
                submissionMapper.updateSubmissionStatus(1, c.getId());
                c.setStatus(1);
                conferenceMapper.updateByPrimaryKey(c);
            }
        }
    }
}
