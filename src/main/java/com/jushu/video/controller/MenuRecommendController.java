package com.jushu.video.controller;


import com.jushu.video.api.Response;
import com.jushu.video.common.IpUtil;
import com.jushu.video.entity.MenuRecommend;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.service.IGmOperationService;
import com.jushu.video.service.IMenuRecommendService;
import com.jushu.video.service.IMovieMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 客户端-推荐列表配置表 前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-02-07
 */
@Controller
@RequestMapping("/video/menu-recommend")
public class MenuRecommendController {


    @Autowired
    private IMenuRecommendService iMenuRecommendService;

    @Autowired
    private IGmOperationService iGmOperationService;

    @Autowired
    private IMovieMainService iMovieMainService;


    @GetMapping("/list")
    public String list(){ return "menuRecommendList";}


    @PostMapping("/list")
    @ResponseBody
    public Response list(HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "查询推荐榜单列表";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "查询成功";
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        if (flag) {
            List<MenuRecommend> lists = iMenuRecommendService.recommendList();
            return new Response(lists);
        } else {
            return new Response("数据出现问题，请联系管理员!");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Response update(@RequestBody String [] movieIds, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "删除推荐榜单电影";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "修改成功";
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        String num = null;
        if (flag) {
            for (int i = 0; i < movieIds.length; i++) {
                num = movieIds[i];
            }
            boolean update = iMenuRecommendService.update(Integer.valueOf(num), null);
            if(update) {
                return new Response("删除成功");
            } else {
                return new Response("删除失败");
            }
        } else {
            return new Response("删除失败");
        }
    }

    @PostMapping("/insert")
    @ResponseBody
    public Response insert(@RequestBody(required = false) MenuRecommend menuRecommend, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "添加推荐榜单电影";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "添加成功";
        if(menuRecommend.getTitle() == null) {
            return new Response("标题不能为空!");
        }
        //以逗号切割该字段
        String [] splits = menuRecommend.getMovieName().split(",");
        //用于接收郄格完的电影名
        String movieName = null;
        //接收id集合
        StringBuilder movieIds = new StringBuilder();
        //电影表实体
        MovieMain movieMainOne = null;
        //接收id
        String movieId = null;
        //循环当前数组
        if(splits.length > 1) {
            for (int i = 0; i < splits.length; i++) {
                movieName = splits[i];
                //根据电影名查找该电影或电视剧是否存在，电影或电视剧查找为精确查找
                movieMainOne = iMovieMainService.getMovieMainOne(movieName);
                if (movieMainOne == null) {
                    return new Response("该电影不存在!");
                }
                movieIds.append(movieMainOne.getId() + ",");
            }
            movieId = movieIds.substring(0, movieIds.length() - 1);
        } else {
            for (int i = 0; i < splits.length; i++) {
                movieName = splits[i];
                movieMainOne = iMovieMainService.getMovieMainOne(movieName);
                if (movieMainOne == null) {
                    return new Response("该电影不存在!");
                }
                movieIds.append(String.valueOf(movieMainOne.getId()));
            }
        }
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        if (flag) {
            int recommendId = 0;
            if(menuRecommend.getTitle().equals("热门电影")) {
                recommendId = 1;
            }
            if(menuRecommend.getTitle().equals("热门美剧")) {
                recommendId = 2;
            }
            if(menuRecommend.getTitle().equals("热门动漫")) {
                recommendId = 3;
            }
            if(menuRecommend.getTitle().equals("热门综艺")) {
                recommendId = 4;
            }
            boolean update = iMenuRecommendService.update(recommendId, movieId);
            if(update) {
                return new Response("添加成功");
            } else {
                return new Response("添加失败");
            }
        } else {
            return new Response("添加失败");
        }
    }
}
