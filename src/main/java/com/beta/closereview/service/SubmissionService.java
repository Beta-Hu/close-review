package com.beta.closereview.service;

import com.beta.closereview.dao.CommentDao;
import com.beta.closereview.dao.UserDao;
import com.beta.closereview.form.CommentForm;
import com.beta.closereview.form.SubmissionForm;
import com.beta.closereview.mapper.ConferenceMapper;
import com.beta.closereview.mapper.SubmissionMapper;
import com.beta.closereview.mapper.UserMapper;
import com.beta.closereview.pojo.Comment;
import com.beta.closereview.pojo.Conference;
import com.beta.closereview.pojo.Submission;
import com.beta.closereview.pojo.User;
import com.beta.closereview.vo.CommentVo;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import com.beta.closereview.vo.SubmissionVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
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
    private ConferenceMapper conferenceMapper;
    @Resource
    private UserDao userDao;
    @Resource
    private CommentDao commentDao;

    /**
     * 列出指定会议的所有录用submission
     *
     * @param conferenceId 会议id
     * @return 返回给前端的Submission基本信息构成的数组
     */
    public List<SimplifiedSubmissionVo> listAcceptedSubmission(Integer conferenceId) {
        List<SimplifiedSubmissionVo> submissionVos = submissionMapper.listAcceptedSubmissions(conferenceId);
        List<Integer> submissionIds = submissionVos.stream()
                .map(SimplifiedSubmissionVo::getId)
                .collect(Collectors.toList());
        List<Set<Integer>> authorIds = userDao.getAuthors(submissionIds);
        Conference conference = conferenceMapper.selectByPrimaryKey(conferenceId);
        Set<Integer> authorIdsSet = new HashSet<>();
        List<User> authors;

        authorIds.forEach(authorIdsSet::addAll);

        Map<Integer, User> users = userMapper.listUsersByIds(new ArrayList<>(authorIdsSet))
                .stream().collect(Collectors.toMap(User::getId, User -> User));

        SimplifiedSubmissionVo simplifiedSubmissionVo;
        for (int i = 0; i < submissionVos.size(); i++) {
            simplifiedSubmissionVo = submissionVos.get(i);
            simplifiedSubmissionVo.setConferenceName(conference.getName());
            authors = new ArrayList<>();
            for (Integer id : authorIds.get(i))
                authors.add(users.get(id));

            simplifiedSubmissionVo.setAuthor(authors);
            submissionVos.remove(i);
            submissionVos.add(i, simplifiedSubmissionVo);
        }
        return submissionVos;
    }

    /**
     * 在accepted-paper.html页面显示指定的(已接收的，对所有用户可查看的)submission的详情
     *
     * @param submissionId submission的id
     * @return 返回给前端的submission的信息
     */
    public SubmissionVo showSubmissionDetail(Integer submissionId) {
        Submission submission = submissionMapper.selectByPrimaryKey(submissionId);
        Conference conference = conferenceMapper.selectByPrimaryKey(submission.getConference());

        List<Integer> authorIds = new ArrayList<>(userDao.getAuthor(submissionId));
        List<User> authors = userMapper.listUsersByIds(authorIds);
        List<Comment> comments = commentDao.getComments(submissionId);

        SubmissionVo submissionVo = new SubmissionVo();
        BeanUtils.copyProperties(submission, submissionVo);
        submissionVo.setConference(conference);
        submissionVo.setAuthor(authors);
        submissionVo.setComments(comments);
        return submissionVo;
    }

    /**
     * 在submission.html页面显示指定的(对author和reviewer的)submission的详情
     *
     * @param submissionId submission的id
     * @param userId       author或reviewer的id
     * @return 返回给前端的submission的信息
     */
    public SubmissionVo showSubmissionDetail(Integer submissionId, Integer userId) {
        Submission submission = submissionMapper.selectByPrimaryKey(submissionId);
        Conference conference = conferenceMapper.selectByPrimaryKey(submission.getConference());

        List<Integer> authorIds = new ArrayList<>(userDao.getAuthor(submissionId));
        if (authorIds.contains(userId)) {
            List<User> authors = userMapper.listUsersByIds(authorIds);

            SubmissionVo submissionVo = new SubmissionVo();
            BeanUtils.copyProperties(submission, submissionVo);
            submissionVo.setConference(conference);
            submissionVo.setAuthor(authors);
            return submissionVo;
        } else {
            List<Integer> reviewerIds = new ArrayList<>(userDao.getReviewerOfSubmission(submissionId));
            if (reviewerIds.contains(userId)) {
                SubmissionVo submissionVo = new SubmissionVo();
                BeanUtils.copyProperties(submission, submissionVo);
                submissionVo.setConference(conference);
                return submissionVo;
            }
        }
        return null;
    }

    /**
     * 在conference-active.html页面显示的用户提交的submission的基本信息
     *
     * @param conferenceId 会议id
     * @param authorId     author的id
     * @return 该用户在该会议中提交的submission的基本信息列表
     */
    public List<SimplifiedSubmissionVo> listSubmissions(Integer conferenceId, Integer authorId) {
        Set<Integer> asAuthor = userDao.getSubmissionOfAuthor(authorId);
        Set<Integer> asReviewer = userDao.getReviewOfReviewer(authorId);
        ArrayList<Integer> submissionIds = new ArrayList<>(asAuthor);
        submissionIds.addAll(asReviewer);

        List<SimplifiedSubmissionVo> submissionVos =
                submissionMapper.listSubmissionBySids(conferenceId, submissionIds);

        for (SimplifiedSubmissionVo s : submissionVos) {
            if (asAuthor.contains(s.getId()))
                s.setRole(0);
            else
                s.setRole(1);
        }
        return submissionVos;
    }

    /**
     * 列出用户需要进行review的submission(review清单)
     *
     * @param conferenceId 会议id
     * @param reviewId     用户id
     * @return
     */
    public List<SimplifiedSubmissionVo> listReviews(Integer conferenceId, Integer reviewId) {
        Set<Integer> submissionIds = userDao.getSubmissionOfAuthor(reviewId);
        return submissionMapper.listSubmissionBySids(conferenceId, new ArrayList<>(submissionIds));
    }

    /**
     * 在profile.html页面展示的用户已经被接收的全部submission的基本信息
     *
     * @param authorId 用户id
     * @return 该用户所有以被接接收的submission的基本信息列表
     */
    public List<SimplifiedSubmissionVo> listPublications(Integer authorId) {
        Set<Integer> submissionIds = userDao.getSubmissionOfAuthor(authorId);
        List<Submission> submissions = submissionMapper.listPublicationBySids(new ArrayList<>(submissionIds));
        List<Integer> conferenceIds =
                submissions.stream().map(Submission::getConference).distinct().collect(Collectors.toList());
        Map<Integer, String> conferenceNames =
                conferenceMapper.listConferenceByCids(conferenceIds).stream()
                        .collect(Collectors.toMap(Conference::getId, Conference::getName));

        List<SimplifiedSubmissionVo> simplifiedSubmissionVoList = new ArrayList<>();
        SimplifiedSubmissionVo simplifiedSubmissionVo;
        for (Submission s : submissions) {
            simplifiedSubmissionVo = new SimplifiedSubmissionVo();
            simplifiedSubmissionVo.setId(s.getId());
            simplifiedSubmissionVo.setTitle(s.getTitle());
            simplifiedSubmissionVo.setConferenceName(conferenceNames.get(s.getConference()));
            simplifiedSubmissionVoList.add(simplifiedSubmissionVo);
        }
        return simplifiedSubmissionVoList;
    }

    /**
     * 用户创建或更新submission。当form中id为0时表明该submission为新创建的，否则为更新的。
     *
     * @param form 前端用户填写的submission内容构成的表单
     * @param user 用户id
     */
    public void updateSubmission(SubmissionForm form, User user) {
        String[] authorWithEmails = form.getAuthor().replace(" ", "").split("[(|)|;]");
        List<String> emails = new ArrayList<>();

        for (String authorWithEmail : authorWithEmails)
            if (authorWithEmail.contains("@"))
                emails.add(authorWithEmail);

        List<User> users = userMapper.listUsersByEmails(emails);

        Submission submission = new Submission();
        BeanUtils.copyProperties(form, submission);
        submission.setStatus(0);

        if (submission.getId().equals(0)) {
            submission.setId(null);
            submission.setCorresponding(user.getId());
            submissionMapper.insertSelective(submission);
        } else {
            submissionMapper.updateByPrimaryKeySelective(submission);
        }
        userDao.addAuthor(submission.getId(),
                users.stream().map(User::getId).collect(Collectors.toList()));
    }

    public SubmissionVo getCommentsOfReviewer(Integer submissionId, Integer reviewerId) {
        SubmissionVo submissionVo = showSubmissionDetail(submissionId, reviewerId);
        if (submissionVo != null) {
            Comment comments = commentDao.getComment(submissionId, reviewerId);
            List<Comment> commentList = new ArrayList<>();
            commentList.add(comments);
            submissionVo.setComments(commentList);
        }
        return submissionVo;
    }

    public boolean addCommentsOfReviewer(Integer reviewerId, CommentForm form) {
        Set<Integer> reviewerList = userDao.getReviewerOfSubmission(form.getSubmissionId());
        if (!reviewerList.contains(reviewerId))
            return false;

        Comment comment = new Comment();
        BeanUtils.copyProperties(form, comment);
        comment.setReviewer(reviewerId);
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        commentDao.addComment(form.getSubmissionId(), commentList);
        return true;
    }
}
