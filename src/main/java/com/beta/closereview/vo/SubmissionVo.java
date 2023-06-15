package com.beta.closereview.vo;

import com.beta.closereview.pojo.Comment;
import com.beta.closereview.pojo.Conference;
import com.beta.closereview.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class SubmissionVo {
    private Integer id;
    private String title;
    private Integer corresponding;
    private List<User> author;
    private String file;
    private String supportMaterial;
    private Conference conference;
    private List<Comment> comments;
}
