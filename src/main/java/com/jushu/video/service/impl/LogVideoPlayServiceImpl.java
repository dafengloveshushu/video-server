package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.LogVideoPlay;
import com.jushu.video.mapper.LogVideoPlayMapper;
import com.jushu.video.service.ILogVideoPlayService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户点播日志表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-01-14
 */
@Service
public class LogVideoPlayServiceImpl extends ServiceImpl<LogVideoPlayMapper, LogVideoPlay> implements ILogVideoPlayService {
    @Override
    public List<LogVideoPlay> selectListPage(Page page, ParamFilter paramFilter) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (paramFilter.getParam() != null) {
            Map<String, Object> map = new HashMap<>();
            map = paramFilter.getParam();
            String tags = (String) map.get("tags");
            String movieName = (String) map.get("movieName");
            String movieType = (String) map.get("movieType");
            if(tags != null) {
                queryWrapper.like("mm.tags", tags);
            }
            if(movieName != null) {
                queryWrapper.like("lvp.movieName", movieName);
            }
            if(movieType != null) {
                queryWrapper.like("mm.movieType", movieType);
            }
        }
        queryWrapper.groupBy("mm.id");
        queryWrapper.orderByDesc("counts");
        return baseMapper.selectListPage(page, queryWrapper);
    }

//    @Override
////    public List<LogVideoPlay> logVideoPlayListPage(String tags, String movieName, Page page)
//    public List<LogVideoPlay> logVideoPlayListPage(Page page) {
////        return baseMapper.selectListPage(tags, movieName, page);
//        return baseMapper.selectListPage(page);
//    }
}
