package com.beta.closereview.form;

import lombok.Data;

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
