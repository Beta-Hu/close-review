package com.beta.closereview.controller;

import com.beta.closereview.pojo.User;
import com.beta.closereview.service.SubmissionService;
import com.beta.closereview.service.UserService;
import com.beta.closereview.vo.ProfileVo;
import com.beta.closereview.vo.ResponseVo;
import com.beta.closereview.vo.SimplifiedSubmissionVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private SubmissionService submissionService;

    @PostMapping("/signin")
    public ResponseVo<User> verify(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.verifyUser(email, password)){
            User user = userService.getUser(email);
            request.getSession().setAttribute("user", user);
            return new ResponseVo<>(user);
        }
        return new ResponseVo<>(0x100001, "signin failed", null);
    }

    @PostMapping("/signup")
    public ResponseVo<User> signup(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String organization = request.getParameter("organization");

        User user = userService.getUser(email);
        if (user == null){
            user = new User(username, password, email, organization);
            Integer rowAffect = userService.addUser(user);
            request.getSession().setAttribute("user", user);
        } else{
            if (! password.equals(user.getPassword()))
                return new ResponseVo<>(0x100005, "user exists", null);
        }
        return new ResponseVo<>(user);
    }

    @GetMapping("/signout")
    public ResponseVo<String> signout(HttpServletRequest request){
        request.getSession().setAttribute("user", null);
        return new ResponseVo<>();
    }

    @GetMapping("/profile")
    public ResponseVo<ProfileVo> profile(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<SimplifiedSubmissionVo> submissionVos =
                submissionService.listPublications(user.getId());

        return new ResponseVo<>(new ProfileVo(user, submissionVos));
    }
}
