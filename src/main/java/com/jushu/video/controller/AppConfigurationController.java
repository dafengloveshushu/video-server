package com.jushu.video.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.Pages;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.api.Response;
import com.jushu.video.common.IpUtil;
import com.jushu.video.entity.AppConfiguration;
import com.jushu.video.entity.GmOperation;
import com.jushu.video.service.IAppConfigurationService;
import com.jushu.video.service.IGmOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@Controller
@RequestMapping("/video/app-configuration")
public class AppConfigurationController {

    @Autowired
    private IAppConfigurationService iAppConfigurationService;

    @Autowired
    private IGmOperationService iGmOperationService;



    @GetMapping("/list")
    public String list(){
        return "appConfigurationList";
    }

    @PostMapping("/list")
    @ResponseBody
    public Response list(@RequestBody ParamFilter queryFilter, HttpServletRequest request, HttpSession session) {
        //new 一个mybatis plus分页对象
        Page<AppConfiguration> page = new Page<>();
        //pages为自己封装的分页工具类，对应页面
        Pages pages = queryFilter.getPage();
        //如果pages不为空，则为page放入当前页、每页显示条数
        if(pages != null) {
            page.setCurrent(pages.getPageNo());
            page.setSize(pages.getPageSize());
        } else {
            //如果pages为空则默认当前页为第一页，每页显示10条
            page.setCurrent(1);
            page.setSize(10);
        }
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "查询配置列表";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "查询成功";
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        if (flag) {
            Page<AppConfiguration> appConfigurationPage = iAppConfigurationService.appConfigurationListPage(page, queryFilter);
            pages.setResultCount((int) appConfigurationPage.getTotal());
            return new Response(appConfigurationPage.getRecords(), pages);
        } else {
            return new Response("数据出现错误，请联系管理员!");
        }
    }


    @PostMapping("/insert")
    @ResponseBody
    public Response insert(@RequestBody(required = false) AppConfiguration appConfiguration, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "新增配置信息";
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


    @PostMapping("/update")
    @ResponseBody
    public Response update(@RequestBody(required = false) AppConfiguration appConfiguration, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "修改配置信息";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "修改成功";
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        if(flag) {
            boolean update = iAppConfigurationService.update(appConfiguration);
            if (update) {
                return new Response("修改成功!");
            } else {
                return new Response("修改失败!");
            }
        } else {
            return new Response("修改失败!");
        }
    }


    @PostMapping("/detail")
    @ResponseBody
    public Response detail(@RequestBody Integer id) {
        AppConfiguration appConfiguration = iAppConfigurationService.getAppConfiguration(id);
        if (appConfiguration != null) {
            return new Response(appConfiguration);
        } else {
            return new Response("出现数据问题,请联系管理员!");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Response delete(@RequestBody String [] ids, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "删除配置信息";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "删除成功";
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        if(flag) {
            boolean delete = iAppConfigurationService.delete(ids);
            if (delete) {
                return new Response("删除成功!");
            } else {
                return new Response("删除失败!");
            }
        } else {
            return new Response("删除失败!");
        }
    }

}
