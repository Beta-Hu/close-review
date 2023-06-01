package com.beta.closereview.dao;

import lombok.Data;

@Data
public class Review {
    private Integer reviewer;

    private Integer submission;

    private Integer initialScore;

    private Integer confidence;

    private Integer finalScore;

    private String initialComment;

    private String rebuttal;

    private String finalComment;
}