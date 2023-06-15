package com.beta.closereview.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private User reviewer;
    private List<String> initialComments;
    private List<String> rebuttal;
    private Integer initialScore;
    private String finalComments;
    private Integer finalScore;
}
