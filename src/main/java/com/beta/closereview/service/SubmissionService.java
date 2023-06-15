package com.beta.closereview.service;

import com.beta.closereview.dao.CommentDao;
import com.beta.closereview.form.SubmissionForm;
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
import org.springframework.transaction.annotation.Transactional;

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
        Conference conference = conferenceMapper.selectByPrimaryKey(conferenceId);
        Set<Integer> authorIdsSet = new HashSet<>();
        List<User> authors;

        authorIds.forEach(authorIdsSet::addAll);

        Map<Integer, User> users = userMapper.getUsersByIds(new ArrayList<>(authorIdsSet))
                .stream().collect(Collectors.toMap(User::getId, User -> User));

        SimplifiedSubmissionVo simplifiedSubmissionVo;
        for (int i = 0; i < submissionVos.size(); i++){
            simplifiedSubmissionVo = submissionVos.get(i);
            simplifiedSubmissionVo.setConferenceName(conference.getName());
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

    public SubmissionVo showSubmissionDetail(Integer submissionId, Integer userId){
        Submission submission = submissionMapper.selectByPrimaryKey(submissionId);
        Conference conference = conferenceMapper.selectByPrimaryKey(submission.getConference());

        List<Integer> authorIds = new ArrayList<>(commentDao.getAuthor(submissionId));
        if (authorIds.contains(userId)){
            List<User> authors = userMapper.getUsersByIds(authorIds);

            SubmissionVo submissionVo = new SubmissionVo();
            BeanUtils.copyProperties(submission, submissionVo);
            submissionVo.setConference(conference);
            submissionVo.setAuthor(authors);
            return submissionVo;
        } else
            return null;
    }

    public List<SimplifiedSubmissionVo> listSubmissions(Integer conferenceId, Integer authorId){
        Set<Integer> submissionIds = commentDao.getSubmissionOfAuthor(authorId);
        return submissionMapper.listSubmissionBySids(conferenceId, new ArrayList<>(submissionIds));
    }
    
    public void updateSubmission(SubmissionForm form, User user){
        String[] authorWithEmails = form.getAuthor().replace(" ", "").split("[(|)|;]");
        List<String> emails = new ArrayList<>();

        for (String authorWithEmail: authorWithEmails)
            if (authorWithEmail.contains("@"))
                emails.add(authorWithEmail);

        List<User> users = userMapper.getUsersByEmails(emails);

        Submission submission = new Submission();
        BeanUtils.copyProperties(form, submission);
        submission.setStatus(0);

        if (submission.getId().equals(0)){
            submission.setId(null);
            submission.setCorresponding(user.getId());
            submissionMapper.insertSelective(submission);
        } else{
            submissionMapper.updateByPrimaryKeySelective(submission);
        }
        commentDao.addAuthor(submission.getId(),
                users.stream().map(User::getId).collect(Collectors.toList()));
    }
}
