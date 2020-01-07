package com.jushu.video.controller;


import com.jushu.video.api.R;
import com.jushu.video.entity.GmAdmin;
import com.jushu.video.service.IGmAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理后台管理员用户表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/video/gm-admin")
public class GmAdminController {

    @Autowired
    private IGmAdminService iGmAdminService;

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
