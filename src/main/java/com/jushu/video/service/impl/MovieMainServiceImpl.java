package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.mapper.MovieMainMapper;
import com.jushu.video.service.IMovieMainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<MovieMain> getMovieMainPageList(Page page, ParamFilter paramFilter) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //封装查询条件
        Map<String, Object> map = new HashMap<>();
        String director = (String) map.get("director");
        if(director != null || director != "") {
            queryWrapper.eq("director", director);
        }
        //queryWrapper.eq("director", movieMain.getDirector());

        return baseMapper.selectPage(page, queryWrapper);
    }
}
