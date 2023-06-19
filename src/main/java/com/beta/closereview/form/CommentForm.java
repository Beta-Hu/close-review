package com.beta.closereview.form;

import lombok.Data;

import java.util.List;

@Data
public class CommentForm {
    List<String> initialComments;
    Integer initialScore;
    String finalComments;
    Integer finalScore;
    Integer submissionId;
}
