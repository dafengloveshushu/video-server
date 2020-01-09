package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jushu.video.entity.GmAdmin;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.mapper.MovieMainMapper;
import com.jushu.video.service.IMovieMainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
@Service
public class MovieMainServiceImpl extends ServiceImpl<MovieMainMapper, MovieMain> implements IMovieMainService {

    @Override
    public MovieMain movieList(GmAdmin gmAdmin) {
        QueryWrapper<MovieMain> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.eq("account", gmAdmin.getAccount());
        movieQueryWrapper.eq("password", gmAdmin.getPassword());
        return baseMapper.selectOne(movieQueryWrapper);
    }
}
