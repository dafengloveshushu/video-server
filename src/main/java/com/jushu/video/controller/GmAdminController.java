package com.jushu.video.controller;


import com.jushu.video.api.R;
import com.jushu.video.entity.GmAdmin;
import com.jushu.video.service.IGmAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 管理后台管理员用户表 前端控制器
 * </p>
 * 登录控制器
 * @author chen
 * @since 2020-01-07
 */
@Controller
@RequestMapping("/video/gm-admin")
public class GmAdminController {

    @Autowired
    private IGmAdminService iGmAdminService;

    @PostMapping("/login")
    public ModelAndView login(GmAdmin gmAdmin, HttpSession session) {
        ModelAndView model = new ModelAndView();
        if(gmAdmin.getAccount() == null || "".equals(gmAdmin.getAccount())) {
            session.invalidate();
            model.addObject("msg", "账号不能为空!");
            model.setViewName("login");
            return model;
        }
        if(gmAdmin.getPassword() == null || "".equals(gmAdmin.getPassword())) {
            session.invalidate();
            model.addObject("account", gmAdmin.getAccount());
            model.addObject("msg", "密码不能为空!");
            model.setViewName("login");
            return model;
        }
        GmAdmin admin = iGmAdminService.login(gmAdmin);
        if (admin != null) {
            session.setAttribute("userName",admin.getAccount());
            session.setAttribute("user", admin);
            model.setViewName("forward:index");
            return model;
        } else {
            session.invalidate();
            model.addObject("account", gmAdmin.getAccount());
            model.addObject("msg", "未找到该用户!");
            model.setViewName("login");
            return model;
        }
    }


    @RequestMapping("/index")
    public ModelAndView index(HttpSession session, ModelAndView model){
        model.addObject("user",session.getAttribute("user"));
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }


    @RequestMapping("/save")
    public R save(@RequestBody GmAdmin gmAdmin) {
        if(gmAdmin.getAccount() == null) {
            return R.fail("账号不能为空!");
        }
        if(gmAdmin.getPassword() == null) {
            return R.fail("密码不能为空!");
        }
        if(iGmAdminService.save(gmAdmin)) {
            return R.success("ok");
        } else {
            return R.fail("fail");
        }
    }

}
