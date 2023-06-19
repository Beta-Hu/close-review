package com.beta.closereview.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private Integer reviewer;
    private List<String> initialComments;
    private Integer initialScore;
    private String finalComments;
    private Integer finalScore;
}
