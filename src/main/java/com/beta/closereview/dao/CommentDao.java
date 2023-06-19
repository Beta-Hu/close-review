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
public class CommentDao {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取单个submission的所有reviewer的comments
     * @param submissionId submission的id
     * @return 该submission的所有comments。每个reviewer的comments构成一个内层列表，所有reviewer构成外层列表
     */
    public List<Comment> getComments(Integer submissionId){
        Gson gson = new Gson();
        Map<Object, Object> entries = stringRedisTemplate.opsForHash()
                .entries("submission:comment:" + submissionId);

        return entries.values().stream()
                .map(j -> gson.fromJson((String) j, Comment.class)).collect(Collectors.toList());
    }

    /**
     * 获取单个submission的单个reviewer的comments
     * @param submissionId submission的id
     *
     * @return 该submission的所有comments。每个reviewer的comments构成一个内层列表，所有reviewer构成外层列表
     */
    public Comment getComment(Integer submissionId, Integer reviewerId){
        Gson gson = new Gson();
        Object jsonComments = stringRedisTemplate.opsForHash()
                .get("submission:comment:" + submissionId, reviewerId.toString());

        return gson.fromJson((String) jsonComments, Comment.class);
    }

    /**
     * 单个reviewer添加对单个submission的comment
     * @param submissionId submission的id
     * @param comments 该reviewer对该submission的所有comments
     */
    public void addComment(Integer submissionId, List<Comment> comments){
        Gson gson = new Gson();
        for (Comment comment: comments)
            stringRedisTemplate.opsForHash()
                    .put("submission:comment:" + submissionId.toString(),
                            comment.getReviewer().toString(), gson.toJson(comment));
    }
}
