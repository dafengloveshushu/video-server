package com.jushu.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.MovieMain;
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
public interface IMovieMainService extends IService<MovieMain> {


    /**
     * 根据条件分页查询
     * @param paramFilter 分页条件工具类
     * @return list
     */
    Page<MovieMain> getMovieMainPageList(Page page, ParamFilter paramFilter);

    Boolean delete(String[] movieIds) throws RuntimeException;

    Boolean deleteVip(String[] movieIds) throws RuntimeException;

    Boolean addVip(String[] movieIds) throws RuntimeException;

    /**
     * 根据电影名查询电影
     * @param movieName 电影名
     * @return 电影记录
     */
    MovieMain getMovieMainOne(String movieName);

    /**
     * 根据电影id查询该条电影记录
     * @param movieId 电影id
     * @return 电影记录
     */
    MovieMain getMovieMainById(Integer movieId);
}
