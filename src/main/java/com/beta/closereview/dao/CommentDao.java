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

    @Deprecated
    public List<Set<String>> getComments(List<Integer> submissionIds){
        // 获取多个submission的所有reviewer的comments
        List<Set<String>> commentsList = new ArrayList<>();
        for (Integer id: submissionIds){
            Set<String> comments = stringRedisTemplate.opsForSet()
                    .members("authorList:" + id.toString());
            commentsList.add(comments);
        }
        return commentsList;
    }

    public List<List<String>> getComment(Integer submissionId){
        // 获取单个submission的所有reviewer的comments
        Gson gson = new Gson();
        Set<String> jsonComments = stringRedisTemplate.opsForSet()
                .members("commentList:" + submissionId.toString());
        List<List<String>> comments = new ArrayList<>();

        for (String jsonComment: jsonComments){
            comments.add(gson.fromJson(jsonComment, List.class));
        }
        return comments;
    }

    public void addComment(Integer submissionId, List<String> comments){
        // 添加单个reviewer对单个submission的多条comment
        Gson gson = new Gson();
        stringRedisTemplate.opsForSet()
                .add("commentList:" + submissionId.toString(), gson.toJson(comments));
    }
}
