package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.GmAdmin;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.mapper.MovieMainMapper;
import com.jushu.video.mapper.MoviePartsMapper;
import com.jushu.video.service.IMovieMainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private MoviePartsMapper moviePartsMapper;

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

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean delete(String[] idList) throws RuntimeException{
        try {
            if (idList == null || idList.length <= 0) {
                return false;
            }
            Map<String, Object> map = new HashMap<>();
            for (String id : idList) {
                map.put("movieId", Integer.valueOf(id));
                moviePartsMapper.deleteByMap(map);
            }
            return baseMapper.deleteBatchIds(Arrays.asList(idList)) > 0;
        }catch (RuntimeException e){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVip(String[] idList) throws RuntimeException{
        try {
            MovieMain movieMain = new MovieMain();
            movieMain.setIsVip(false);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            if (idList == null || idList.length <= 0) {
                return false;
            }
            updateWrapper.in("id", idList);
            return baseMapper.update(movieMain, updateWrapper) > 0;
        }catch (RuntimeException e){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean addVip(String[] idList) throws RuntimeException{
        try {
            MovieMain movieMain = new MovieMain();
            movieMain.setIsVip(true);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            if (idList == null || idList.length <= 0) {
                return false;
            }
            updateWrapper.in("id", idList);
            return baseMapper.update(movieMain, updateWrapper) > 0;
        }catch (RuntimeException e){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public MovieMain getMovieMainOne(String movieName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", movieName);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public MovieMain getMovieMainById(Integer movieId) {
        MovieMain movieMain = baseMapper.selectById(movieId);
        if(movieMain != null) {
            return movieMain;
        }
        return null;
    }
}
