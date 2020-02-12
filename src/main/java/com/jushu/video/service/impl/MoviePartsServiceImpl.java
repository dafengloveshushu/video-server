package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.entity.MovieParts;
import com.jushu.video.mapper.MoviePartsMapper;
import com.jushu.video.service.IMoviePartsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
@Service
public class MoviePartsServiceImpl extends ServiceImpl<MoviePartsMapper, MovieParts> implements IMoviePartsService {
    @Override
    public MovieParts detail(Integer movieId){
        return baseMapper.selectById(movieId);
    }

    @Override
    public List<MovieParts> detailList(Integer movieId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("movieId", movieId);
        queryWrapper.orderByAsc("title");
        List<MovieParts> movieParts = baseMapper.selectList(queryWrapper);
        if(movieParts != null) {
            return movieParts;
        }
        return null;
    }
}
