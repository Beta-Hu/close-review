package com.beta.closereview.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AboutController {
    @GetMapping("/about")
    public String about(){
        return "../static/about";
    }
}
