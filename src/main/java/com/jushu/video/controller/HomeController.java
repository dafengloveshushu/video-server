package com.jushu.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 大奉
 * @date 2020/1/8 10:56
 * @blame 大奉
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String toLogin(){
        return "login";
    }

}
