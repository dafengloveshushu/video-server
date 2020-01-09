package com.jushu.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.entity.MovieMain;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param page 分页工具类
     * @param movieMain 查询条件实体类
     * @return page集合
     */
    Page<MovieMain> getMovieMainPageList(Page page, MovieMain movieMain);
}
