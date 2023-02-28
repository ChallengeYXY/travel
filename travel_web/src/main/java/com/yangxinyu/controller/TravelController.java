package com.yangxinyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TravelController {
    @RequestMapping("/main")
    public String toMain(){
        return "pages/main";
    }
}
