package com.jushu.video.controller;


import com.jushu.video.api.Response;
import com.jushu.video.common.IpUtil;
import com.jushu.video.entity.AppConfiguration;
import com.jushu.video.service.IAppConfigurationService;
import com.jushu.video.service.IGmOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-02-28
 */
@RestController
@RequestMapping("/video/app-configuration")
public class AppConfigurationController {

    @Autowired
    private IAppConfigurationService iAppConfigurationService;

    @Autowired
    private IGmOperationService iGmOperationService;

    @PostMapping("/insert")
    public Response insert(@RequestBody(required = false) AppConfiguration appConfiguration, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "设置VIP电影播放时间段";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "添加成功";
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        if(flag) {
            boolean insert = iAppConfigurationService.insert(appConfiguration);
            if (insert) {
                return new Response("添加成功!");
            } else {
                return new Response("添加失败!");
            }
        } else {
            return new Response("添加失败!");
        }
    }

}
