package com.beta.closereview.dao;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CommentDao {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public List<Set<Integer>> getAuthors(List<Integer> submissionIds){
        // 获取多个submission的作者的id列表
        List<Set<Integer>> authorIds = new ArrayList<>();
        for (Integer id: submissionIds){
            Set<Integer> authorIdsOfSubmission = stringRedisTemplate.opsForSet()
                    .members("authorList:" + id.toString())
                    .stream().map(Integer::parseInt)
                    .collect(Collectors.toSet());
            authorIds.add(authorIdsOfSubmission);
        }
        return authorIds;
    }

    public Set<Integer> getAuthor(Integer submissionId){
        // 获取单个submission的所有作者
        return stringRedisTemplate.opsForSet()
                .members("authorList:" + submissionId.toString())
                .stream().map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

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

    public Set<Integer> getSubmissionOfAuthor(Integer authorId){
        return stringRedisTemplate.opsForSet()
                .members("submissionList:" + authorId.toString())
                .stream().map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public void addAuthor(Integer submissionId, List<Integer> authorIds){
        for (Integer authorId: authorIds){
            // 为该submission添加一个author
            stringRedisTemplate.opsForSet()
                    .add("authorList:" + submissionId, authorId.toString());
            // 为该用户添加一个submission记录
            stringRedisTemplate.opsForSet()
                    .add("submissionList:" + authorId, submissionId.toString());
        }

    }

    public void addComment(Integer submissionId, List<String> comments){
        // 添加单个reviewer对单个submission的多条comment
        Gson gson = new Gson();
        stringRedisTemplate.opsForSet()
                .add("commentList:" + submissionId.toString(), gson.toJson(comments));
    }
}
