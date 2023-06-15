package com.beta.closereview.vo;

import com.beta.closereview.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class SimplifiedSubmissionVo {
    private Integer id;
    private String title;
    private List<User> author;
}
