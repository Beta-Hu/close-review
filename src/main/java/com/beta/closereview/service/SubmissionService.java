package com.beta.closereview.service;

import com.beta.closereview.dao.CommentDao;
import com.beta.closereview.mapper.ConferenceMapper;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.mapper.UserMapper;
import com.beta.closereview.pojo.Conference;
import com.beta.closereview.pojo.Submission;
import com.beta.closereview.pojo.User;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import com.beta.closereview.vo.SubmissionVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubmissionService {
    @Resource
    private SubmissionMapper submissionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentDao commentDao;
    @Resource
    private ConferenceMapper conferenceMapper;

    public List<SimplifiedSubmissionVo> listAcceptedSubmission(Integer conferenceId){
        List<SimplifiedSubmissionVo> submissionVos = submissionMapper.listAcceptedSubmissions(conferenceId);
        List<Integer> submissionIds = submissionVos.stream()
                .map(SimplifiedSubmissionVo::getId)
                .collect(Collectors.toList());
        List<Set<Integer>> authorIds = commentDao.getAuthors(submissionIds);
        Set<Integer> authorIdsSet = new HashSet<>();
        List<User> authors;

        authorIds.forEach(authorIdsSet::addAll);

        Map<Integer, User> users = userMapper.getUsersByIds(new ArrayList<>(authorIdsSet))
                .stream().collect(Collectors.toMap(User::getId, User -> User));

        SimplifiedSubmissionVo simplifiedSubmissionVo;
        for (int i = 0; i < submissionVos.size(); i++){
            simplifiedSubmissionVo = submissionVos.get(i);
            authors = new ArrayList<>();
            for (Integer id: authorIds.get(i))
                authors.add(users.get(id));

            simplifiedSubmissionVo.setAuthor(authors);
            submissionVos.remove(i);
            submissionVos.add(i, simplifiedSubmissionVo);
        }
        return submissionVos;
    }

    public SubmissionVo showSubmissionDetail(Integer submissionId){
        Submission submission = submissionMapper.selectByPrimaryKey(submissionId);
        Conference conference = conferenceMapper.selectByPrimaryKey(submission.getConference());

        List<Integer> authorIds = new ArrayList<>(commentDao.getAuthor(submissionId));
        List<User> authors = userMapper.getUsersByIds(authorIds);


        SubmissionVo submissionVo = new SubmissionVo();
        BeanUtils.copyProperties(submission, submissionVo);
        submissionVo.setConference(conference);
        submissionVo.setAuthor(authors);
        return submissionVo;
    }
}
