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
        map = paramFilter.getParam();
        if(map != null) {
            String movieType = (String) map.get("movieType");
            String name = (String) map.get("name");
            String tag = (String) map.get("tag");
            if (movieType != null && !movieType.equals("")) {
                queryWrapper.eq("movieType", movieType);
            }
            if (name != null && !name.equals("")) {
                queryWrapper.like("name", name);
            }
            if (tag != null && !tag.equals("")) {
                queryWrapper.like("tags", tag);
            }
            //queryWrapper.eq("director", movieMain.getDirector());
        }
        return baseMapper.selectPage(page, queryWrapper);
    }
}
