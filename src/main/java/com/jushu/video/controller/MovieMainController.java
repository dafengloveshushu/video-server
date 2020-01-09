package com.jushu.video.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.service.IMovieMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
@RestController
@RequestMapping("/video/movie-main")
public class MovieMainController {


    @Autowired
    private IMovieMainService iMovieMainService;

    @RequestMapping("/list")
    public List<MovieMain> list(Page page, MovieMain movieMain, ModelAndView modelAndView) {
        //当前页
        page.setCurrent(1);
        //每页显示条数
        page.setSize(10);
        Page pages = iMovieMainService.getMovieMainPageList(page, movieMain);
        List<MovieMain> movieMainList = pages.getRecords();
        return movieMainList;
    }

}
