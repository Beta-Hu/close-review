package com.beta.closereview.dao;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class CommentDao {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取多个submission各自的reviewer的comments
     * @param submissionIds submission的id
     * @return 多个submission各自的comments集合构成的列表
     */
    @Deprecated
    public List<Set<String>> getComments(List<Integer> submissionIds){
        List<Set<String>> commentsList = new ArrayList<>();
        for (Integer id: submissionIds){
            Set<String> comments = stringRedisTemplate.opsForSet()
                    .members("authorList:" + id.toString());
            commentsList.add(comments);
        }
        return commentsList;
    }

    /**
     * 获取单个submission的所有reviewer的comments
     * @param submissionId submission的id
     * @return 该submission的所有comments。每个reviewer的comments构成一个内层列表，所有reviewer构成外层列表
     */
    public List<List<String>> getComment(Integer submissionId){
        Gson gson = new Gson();
        Set<String> jsonComments = stringRedisTemplate.opsForSet()
                .members("commentList:" + submissionId.toString());
        List<List<String>> comments = new ArrayList<>();

        for (String jsonComment: jsonComments){
            comments.add(gson.fromJson(jsonComment, List.class));
        }
        return comments;
    }

    /**
     * 单个reviewer添加对单个submission的多条comment
     * @param submissionId submission的id
     * @param comments 该reviewer对该submission的所有comments
     */
    public void addComment(Integer submissionId, List<String> comments){
        Gson gson = new Gson();
        stringRedisTemplate.opsForSet()
                .add("commentList:" + submissionId.toString(), gson.toJson(comments));
    }
}
