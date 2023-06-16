package com.beta.closereview.vo;

import com.beta.closereview.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileVo {
    User user;
    List<SimplifiedSubmissionVo> submissions;
}
