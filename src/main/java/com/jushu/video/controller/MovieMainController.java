package com.jushu.video.controller;


import com.jushu.video.api.Pages;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.api.Response;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.service.IMovieMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/list")
    public String list() {
        return "userList";
    }



    @PostMapping("/list")
    @ResponseBody
    public Response list(@RequestBody ParamFilter queryFilter) {
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
        //返回数据至页面
        return new Response(movieMainList.getRecords(), pages);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Response delete(@RequestBody String[] movieIds) {
        try {
            if (movieIds == null || movieIds.length <= 0) {
                return new Response("电影编号不能为空");
            }
            if (!iMovieMainService.delete(movieIds)) {
                return new Response("删除失败");
            }
            return new Response("删除成功");
        }catch (RuntimeException e){
            return new Response(e.getMessage());
        }
    }

    @PostMapping("/addVip")
    @ResponseBody
    public Response addVip(@RequestBody String[] movieIds) {
        try {
            if (movieIds == null || movieIds.length <= 0) {
                return new Response("电影编号不能为空");
            }
            if (!iMovieMainService.addVip(movieIds)) {
                return new Response("添加标签失败");
            }
            return new Response("添加标签成功");
        }catch (RuntimeException e){
            return new Response(e.getMessage());
        }
    }

    @PostMapping("/delVip")
    @ResponseBody
    public Response deleteVip(@RequestBody String[] movieIds) {
        try {
            if (movieIds == null || movieIds.length <= 0) {
                return new Response("电影编号不能为空");
            }
            if (!iMovieMainService.deleteVip(movieIds)) {
                return new Response("移除标签失败");
            }
            return new Response("移除标签成功");
        }catch (RuntimeException e){
            return new Response(e.getMessage());
        }
    }
}
