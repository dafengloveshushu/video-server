package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jushu.video.entity.MenuMovieType;
import com.jushu.video.entity.MenuRecommend;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.mapper.MenuMovieTypeMapper;
import com.jushu.video.service.IMenuMovieTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户端-筛选菜单配置表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-02-13
 */
@Service
public class MenuMovieTypeServiceImpl extends ServiceImpl<MenuMovieTypeMapper, MenuMovieType> implements IMenuMovieTypeService {

    private Logger logger = LoggerFactory.getLogger(MenuRecommendServiceImpl.class);

    @Override
    public List<MenuMovieType> recommendList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        //查询所有推荐榜单列表
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Boolean delete(String[] ids) {
            if (ids == null || ids.length <= 0) {
                return false;
            }
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.in("id", ids);
            return baseMapper.delete(wrapper) > 0;
        }

    @Override
    public Boolean create(@Param("menuMovieType") MenuMovieType menuMovieType) {
        if(menuMovieType.getId().equals(0)){
            return false;
        }
        return baseMapper.insert(menuMovieType) > 0;
    }
}
