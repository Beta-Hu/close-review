package com.beta.closereview.form;

import com.beta.closereview.pojo.Comment;
import com.beta.closereview.pojo.Conference;
import com.beta.closereview.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class SubmissionForm {
    private Integer id;
    private String title;
    private String abstracts;
    private Integer corresponding;
    private String author;
    private String file;
    private String supportMaterial;
    private Integer conference;
}
