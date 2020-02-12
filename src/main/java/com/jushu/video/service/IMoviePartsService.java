package com.jushu.video.service;

import com.jushu.video.entity.MovieParts;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
public interface IMoviePartsService extends IService<MovieParts> {
    MovieParts detail(Integer movieId);


    List<MovieParts> detailList(Integer movieId);
}
