package com.beta.closereview.controller;

import com.beta.closereview.pojo.User;
import com.beta.closereview.service.UserService;
import com.beta.closereview.vo.ResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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
    public ResponseVo<User> profile(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        // todo: 将已发表的文章添加到profile
        return new ResponseVo<>(0, "success", user);
    }
}
