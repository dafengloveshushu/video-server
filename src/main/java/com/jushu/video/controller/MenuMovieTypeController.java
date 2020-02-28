package com.jushu.video.controller;


import com.jushu.video.api.Response;
import com.jushu.video.common.IpUtil;
import com.jushu.video.entity.MenuMovieType;
import com.jushu.video.entity.MenuRecommend;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.service.IGmOperationService;
import com.jushu.video.service.IMenuMovieTypeService;
import com.jushu.video.service.IMenuRecommendService;
import com.jushu.video.service.IMovieMainService;
import com.jushu.video.service.impl.MenuMovieTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 客户端-筛选菜单配置表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-02-13
 */
@Controller
@RequestMapping("/video/menu-movie-type")
public class MenuMovieTypeController {
        @Autowired
        private IMenuMovieTypeService iMenuMovieTypeService;

        @Autowired
        private IGmOperationService iGmOperationService;


        @GetMapping("/list")
        public String list(){ return "menuMovieTypeList";}

        @PostMapping("/list")
        @ResponseBody
        public Response list(HttpServletRequest request, HttpSession session) {
            //获取当前方法名
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            //操作事件
            String operation = "查询客户端筛选配置列表";
            //当前用户登录IP
            String loginIp = IpUtil.getIpAddr(request);
            //操作是否成功
            int isSuccess = 0;
            //备注：查询成功
            String remark = "查询成功";
            boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            if (flag) {
                List<MenuMovieType> lists = iMenuMovieTypeService.recommendList();
                return new Response(lists);
            } else {
                return new Response("数据出现问题，请联系管理员!");
            }
        }

        @PostMapping("/delete")
        @ResponseBody
        public Response delete(@RequestBody String [] ids, HttpServletRequest request, HttpSession session) {
            //获取当前方法名
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            //操作事件
            String operation = "删除筛选标题";
            //当前用户登录IP
            String loginIp = IpUtil.getIpAddr(request);
            //操作是否成功
            int isSuccess = 0;
            //备注：查询成功
            String remark = "删除成功";
            boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            if(flag) {
                boolean delete = iMenuMovieTypeService.delete(ids);
                if (delete) {
                    return new Response("删除成功!");
                } else {
                    return new Response("删除失败!");
                }
            } else {
                return new Response("删除失败!");
            }
        }

        @PostMapping("/insert")
        @ResponseBody
        public Response insert(@RequestBody(required = false) MenuMovieType menuMovieType, HttpServletRequest request, HttpSession session) {
            //获取当前方法名
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            //操作事件
            String operation = "添加筛选标题";
            //当前用户登录IP
            String loginIp = IpUtil.getIpAddr(request);
            //操作是否成功
            int isSuccess = 0;
            //备注：查询成功
            String remark = "添加成功";
            if(menuMovieType.getTitle() == null) {
                return new Response("标题不能为空!");
            }
            boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            if(flag) {
                boolean update = iMenuMovieTypeService.create(menuMovieType);
                if (update) {
                    return new Response("添加成功!");
                } else {
                    return new Response("添加失败!");
                }
            } else {
                return new Response("添加失败!");
            }
        }
}
