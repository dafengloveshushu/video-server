package com.jushu.video.controller;


import com.jushu.video.api.Pages;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.api.Response;
import com.jushu.video.common.IpUtil;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.entity.MovieParts;
import com.jushu.video.service.IGmOperationService;
import com.jushu.video.service.IMovieMainService;
import com.jushu.video.service.IMoviePartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
@Controller
@RequestMapping("/video/movie-main")
public class MovieMainController {


    @Autowired
    private IMovieMainService iMovieMainService;
    @Autowired
    private IGmOperationService iGmOperationService;
    @Autowired
    private IMoviePartsService iMoviePartsService;


    @GetMapping("/list")
    public String list() {
        return "userList";
    }



    @PostMapping("/list")
    @ResponseBody
    public Response list(@RequestBody ParamFilter queryFilter, HttpServletRequest request, HttpSession session) {
        //new 一个mybatis plus分页对象
        Page<MovieMain> page = new Page<>();
        //pages为自己封装的分页，对应页面
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
        //分页数据集合
        Page<MovieMain> movieMainList = iMovieMainService.getMovieMainPageList(page, queryFilter);
        //得到总记录数，页面上自动计算页数
        pages.setResultCount((int) movieMainList.getTotal());
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "查询电影列表";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功 || 查询失败
        String remark = null;
        if(movieMainList != null) {
            //返回数据至页面
            remark = "查询成功";
            iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            return new Response(movieMainList.getRecords(), pages);
        } else {
            //返回数据至页面
            isSuccess = 1;
            remark = "查询失败";
            iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            return new Response("数据出现错误!");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Response delete(@RequestBody String[] movieIds, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "删除电影记录";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //备注：查询成功 || 查询失败
        String remark = null;
        try {
            if (movieIds == null || movieIds.length <= 0) {
                return new Response("电影编号不能为空");
            }
            if (!iMovieMainService.delete(movieIds)) {
                remark = "操作失败";
                iGmOperationService.saveOperation(method, loginIp, operation, 1, remark, session);
                return new Response("删除失败");
            }
            remark = "操作成功";
            iGmOperationService.saveOperation(method, loginIp, operation, 0, remark, session);
            return new Response("删除成功");
        }catch (RuntimeException e){
            return new Response(e.getMessage());
        }
    }

    @PostMapping("/addVip")
    @ResponseBody
    public Response addVip(@RequestBody String[] movieIds, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "添加VIP标识";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //备注：查询成功 || 查询失败
        String remark = null;
        try {
            if (movieIds == null || movieIds.length <= 0) {
                return new Response("电影编号不能为空");
            }
            if (!iMovieMainService.addVip(movieIds)) {
                remark = "操作失败";
                iGmOperationService.saveOperation(method, loginIp, operation, 1, remark, session);
                return new Response("添加标签失败");
            }
            remark = "操作成功";
            iGmOperationService.saveOperation(method, loginIp, operation, 0, remark, session);
            return new Response("添加标签成功");
        }catch (RuntimeException e){
            return new Response(e.getMessage());
        }
    }

    @PostMapping("/delVip")
    @ResponseBody
    public Response deleteVip(@RequestBody String[] movieIds, HttpServletRequest request, HttpSession session) {
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "移除VIP标识";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //备注：查询成功 || 查询失败
        String remark = null;
        try {
            if (movieIds == null || movieIds.length <= 0) {
                return new Response("电影编号不能为空");
            }
            if (!iMovieMainService.deleteVip(movieIds)) {
                remark = "操作失败";
                iGmOperationService.saveOperation(method, loginIp, operation, 1, remark, session);
                return new Response("移除标签失败");
            }
            remark = "操作成功";
            iGmOperationService.saveOperation(method, loginIp, operation, 0, remark, session);
            return new Response("移除标签成功");
        }catch (RuntimeException e){
            return new Response(e.getMessage());
        }
    }


    @GetMapping("/detail")
    public ModelAndView detailMovieMain(Integer movieId, HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (movieId == 0) {
            modelAndView.addObject("msg", "参数不能为空!");
            return modelAndView;
        }
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "查询电影详情";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功
        String remark = "查询成功";
        boolean flag = iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
        if (flag) {
            MovieMain movieMainById = iMovieMainService.getMovieMainById(movieId);
            if (movieMainById != null) {
                List<MovieParts> movieParts = iMoviePartsService.detailList(movieId);
                if (movieParts != null) {
                    modelAndView.addObject("partsList", movieParts);
                }
                modelAndView.addObject("movie", movieMainById);
                modelAndView.setViewName("movieDetail");
            }
        } else {
            modelAndView.addObject("msg", "数据出现错误!");
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/getMovieTypes")
    @ResponseBody
    public Response getMovieTypes(HttpServletRequest request, HttpSession session) {
        //分页数据集合
        List<String> types = iMovieMainService.getMovieTypes();
        //获取当前方法名
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        //操作事件
        String operation = "查询电影类型";
        //当前用户登录IP
        String loginIp = IpUtil.getIpAddr(request);
        //操作是否成功
        int isSuccess = 0;
        //备注：查询成功 || 查询失败
        String remark = null;
        if(types != null) {
            //返回数据至页面
            remark = "查询成功";
            iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            return new Response(types);
        } else {
            //返回数据至页面
            isSuccess = 1;
            remark = "查询失败";
            iGmOperationService.saveOperation(method, loginIp, operation, isSuccess, remark, session);
            return new Response("数据出现错误!");
        }
    }
}
