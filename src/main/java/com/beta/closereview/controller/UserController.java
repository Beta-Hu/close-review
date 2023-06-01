package com.beta.closereview.controller;

import com.beta.closereview.dao.Organization;
import com.beta.closereview.dao.Submission;
import com.beta.closereview.dao.User;
import com.beta.closereview.enums.StatusEnum;
import com.beta.closereview.service.OrganizationService;
import com.beta.closereview.service.SubmissionService;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

// TODO: 实现登陆成功后跳转，同时地址栏为跳转后的地址

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, ModelMap map) throws IOException {
        if (session.getAttribute("user") != null)
            return console(map, session);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.login(email, password).equals(StatusEnum.OK.getCode())) {
            User user = new User();
            user.setEmail(email);
            session.setAttribute("user", user);
            return console(map, session);
        } else{
            return "../static/login";
        }
    }

    @GetMapping("/console")
    public String console(ModelMap map, HttpSession session){

        User user = (User) session.getAttribute("user");
        user = userService.getUserDetail(user.getEmail());
        List<Submission> submissions = submissionService.getSubmissions(user.getId());
        String organizationName = organizationService.getOrganizationNameById(user.getOrganization());

        map.put("username", user.getUsername());
        map.put("email", user.getEmail());
        map.put("organization", organizationName);
        map.put("publications", submissions);
        map.put("coAuthors", "");
        return "profile";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.setAttribute("user", null);
        return "../static/login";
    }

    /**
     * 用于加载含有thymeleaf的注册页面
     * @param map
     * @return
     */
    @GetMapping("/register")
    public String registerPage(ModelMap map){
        List<Organization> organizations = organizationService.getOrganizations();
        map.put("organizations", organizations);
        return "register";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpSession session, ModelMap map){
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setOrganization(Integer.valueOf(request.getParameter("organization")));
        user.setUsername(request.getParameter("username"));

        userService.register(user);
        session.setAttribute("user", user);
        return console(map, session);
    }
}
