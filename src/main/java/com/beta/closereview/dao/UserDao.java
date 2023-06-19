package com.beta.closereview.dao;

import com.beta.closereview.pojo.Comment;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDao {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取多个submission的作者的id列表
     * @param submissionIds submission的id列表
     * @return authorId构成的列表，每个submission的author构成内层集合，所有submission构成外层列表
     */
    public List<Set<Integer>> getAuthors(List<Integer> submissionIds){
        List<Set<Integer>> authorIds = new ArrayList<>();
        for (Integer id: submissionIds){
            Set<Integer> authorIdsOfSubmission = stringRedisTemplate.opsForSet()
                    .members("submission:author" + id.toString())
                    .stream().map(Integer::parseInt)
                    .collect(Collectors.toSet());
            authorIds.add(authorIdsOfSubmission);
        }
        return authorIds;
    }

    /**
     * 获取单个submission的所有作者id
     * @param submissionId submission的id
     * @return 该submission所有作者id构成的集合
     */
    public Set<Integer> getAuthor(Integer submissionId){
        return stringRedisTemplate.opsForSet()
                .members("submission:author:" + submissionId.toString())
                .stream().map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    /**
     * 获取指定用户的所有(任意状态的)submission的id
     * @param authorId author的id
     * @return 作者包含该用户的submission的id集合
     */
    public Set<Integer> getSubmissionOfAuthor(Integer authorId){
        return stringRedisTemplate.opsForSet()
                .members("user:submit:" + authorId.toString())
                .stream().map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    /**
     * 查看reviewer需要review的submission
     * @param authorId
     * @return
     */
    public Set<Integer> getReviewOfReviewer(Integer authorId){
        return stringRedisTemplate.opsForSet()
                .members("user:review:" + authorId.toString())
                .stream().map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    /**
     * 查看reviewer需要review的submission
     * @param submissionId
     * @return
     */
    public Set<Integer> getReviewerOfSubmission(Integer submissionId){
        Map<Object, Object> entries =
                stringRedisTemplate.opsForHash().entries("submission:comment:" + submissionId);
        return entries.keySet().stream().map(o -> Integer.parseInt((String) o)).collect(Collectors.toSet());
    }

    /**
     * 为reviewer添加需要review的submission
     * @param reviewIds submission的多个review
     * @param submissionId 被review的submission
     */
    public void addReviewer(Integer submissionId, List<Integer> reviewIds){
        Gson gson = new Gson();
        Comment comment = new Comment();
        for (Integer reviewId: reviewIds) {
            comment.setReviewer(reviewId);
            stringRedisTemplate.opsForSet()
                    .add("user:review:" + reviewId, submissionId.toString());
            stringRedisTemplate.opsForHash().put("submission:comment:" + submissionId, reviewId.toString(),
                    gson.toJson(comment));
        }
    }

    /**
     * 为某个submission添加多个作者
     * @param submissionId submission的id
     * @param authorIds 多个author的id列表
     */
    public void addAuthor(Integer submissionId, List<Integer> authorIds){
        for (Integer authorId: authorIds){
            // 为该submission添加一个author
            stringRedisTemplate.opsForSet()
                    .add("submission:author:" + submissionId, authorId.toString());
            // 为该用户添加一个submission记录
            stringRedisTemplate.opsForSet()
                    .add("user:submit:" + authorId, submissionId.toString());
        }
    }
}
