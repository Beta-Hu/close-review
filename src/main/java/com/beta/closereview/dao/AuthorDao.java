package com.beta.closereview.dao;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AuthorDao {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取多个submission的作者的id列表
     * @param submissionIds
     * @return authorId构成的列表，每个submission的author构成内层集合，所有submission构成外层列表
     */
    public List<Set<Integer>> getAuthors(List<Integer> submissionIds){
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

    /**
     * 获取单个submission的所有作者id
     * @param submissionId submission的id
     * @return 该submission所有作者id构成的集合
     */
    public Set<Integer> getAuthor(Integer submissionId){
        return stringRedisTemplate.opsForSet()
                .members("authorList:" + submissionId.toString())
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
                .members("submissionList:" + authorId.toString())
                .stream().map(Integer::parseInt)
                .collect(Collectors.toSet());
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
                    .add("authorList:" + submissionId, authorId.toString());
            // 为该用户添加一个submission记录
            stringRedisTemplate.opsForSet()
                    .add("submissionList:" + authorId, submissionId.toString());
        }

    }
}
