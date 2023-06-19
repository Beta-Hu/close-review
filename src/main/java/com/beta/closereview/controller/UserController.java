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

    /**
     * 用户登录。加载登录界面时自动请求发送校验码进行加密
     * @param request
     * @return
     */
    @PostMapping("/signin")
    public ResponseVo<User> verify(HttpServletRequest request){
        if (request.getParameter("requireCode") != null){
            String verifyCode = String.valueOf((int) (Integer.MAX_VALUE * Math.random()));
            request.getSession().setAttribute("verifyCode", verifyCode);
            return new ResponseVo<>(new User(verifyCode));
        } else{
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (userService.verifyUser(email, password, (String) request.getSession().getAttribute("verifyCode"))){
                User user = userService.getUser(email);
                request.getSession().setAttribute("user", user);
                return new ResponseVo<>(user);
            }
            return new ResponseVo<>(0x100001, "signin failed", null);
        }
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
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

    /**
     * 用户退出登录
     * @param request
     * @return
     */
    @GetMapping("/signout")
    public ResponseVo<String> signout(HttpServletRequest request){
        request.getSession().setAttribute("user", null);
        return new ResponseVo<>();
    }

    /**
     * 查看用户的个人信息
     * @param request
     * @return
     */
    @GetMapping("/profile")
    public ResponseVo<ProfileVo> profile(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<SimplifiedSubmissionVo> submissionVos =
                submissionService.listPublications(user.getId());

        return new ResponseVo<>(new ProfileVo(user, submissionVos));
    }
}
