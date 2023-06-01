package com.beta.closereview.controller;

import com.beta.closereview.dao.User;
import com.beta.closereview.enums.StatusEnum;
import com.beta.closereview.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO: 实现登陆成功后跳转，同时地址栏为跳转后的地址

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, ModelMap map) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.login(email, password).equals(StatusEnum.OK.getCode())) {
            User user = new User();
            user.setEmail(email);
            session.setAttribute("user", user);
            return "profile";
        } else{
            return "../static/login";
        }
    }

    @GetMapping("/console")
    public String console(ModelMap map, HttpSession session){
        User user = (User) session.getAttribute("user");
        user = userService.getUserDetail(user.getEmail());


        map.put("userName", user.getUsername());
        map.put("email", user.getEmail());
        map.put("organization", user.getOrganization());
//        map.put("publications", )
        return "profile";
    }
}
