package com.jushu.video.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.Pages;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.api.Response;
import com.jushu.video.common.IpUtil;
import com.jushu.video.entity.LogVideoPlay;
import com.jushu.video.service.IGmOperationService;
import com.jushu.video.service.ILogVideoPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户点播日志表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-01-14
 */
@Controller
@RequestMapping("/video/log-video-play")
public class LogVideoPlayController {

    @Autowired
    private ILogVideoPlayService iLogVideoPlayService;

    @Autowired
    private IGmOperationService iGmOperationService;


    @GetMapping("/list")
    public String list(){
        return "loginLogList";
    }



    @PostMapping("/list")
    @ResponseBody
    public Response list(@RequestBody ParamFilter queryFilter, HttpServletRequest request, HttpSession session) {
        //new 一个mybatis plus分页对象
        Page<LogVideoPlay> page = new Page<>();
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
        List<LogVideoPlay> logVideoPlayList = iLogVideoPlayService.selectListPage(page, queryFilter);
        page.setRecords(logVideoPlayList);
        //得到总记录数，页面上自动计算页数
        pages.setResultCount((int) page.getTotal());
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "查询播放量记录列表";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功 || 查询失败
        String remark = null;
        if(logVideoPlayList != null) {
            //返回数据至页面
            remark = "查询成功";
            iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            return new Response(page.getRecords(), pages);
        } else {
            //返回数据至页面
            isSuccess = 1;
            remark = "查询失败";
            iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            return new Response("数据出现错误!");
        }
    }
}
