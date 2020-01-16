package com.jushu.video.controller;


import com.jushu.video.api.Response;
import com.jushu.video.entity.MovieParts;
import com.jushu.video.service.IMoviePartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
@RestController
@RequestMapping("/video/movie-parts")
public class MoviePartsController {
    @Autowired
    private IMoviePartsService iMoviePartsService;

    @PostMapping("/detail")
    @ResponseBody
    public Response detail(@RequestBody Integer movieId) {
        if (movieId != null){
            MovieParts movieParts = iMoviePartsService.detail(movieId);
            return new Response(movieParts);
        }
        return new Response("获取视频详情失败");
    }
}
