package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jushu.video.entity.MenuRecommend;
import com.jushu.video.entity.MovieMain;
import com.jushu.video.mapper.MenuRecommendMapper;
import com.jushu.video.mapper.MovieMainMapper;
import com.jushu.video.service.IMenuRecommendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BulkBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户端-推荐列表配置表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-02-07
 */
@Service
public class MenuRecommendServiceImpl extends ServiceImpl<MenuRecommendMapper, MenuRecommend> implements IMenuRecommendService {

    private Logger logger = LoggerFactory.getLogger(MenuRecommendServiceImpl.class);

    @Autowired
    private MovieMainMapper movieMainMapper;

    @Override
    public List<MenuRecommend> recommendList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        //查询所有推荐榜单列表
        List<MenuRecommend> recommendList = baseMapper.selectList(queryWrapper);
        //电影数据集合
        List<MovieMain> movieMainList = null;
        //电影名
        StringBuilder movieName = new StringBuilder();
        //推荐榜单实体类
        MenuRecommend menuRecommend = new MenuRecommend();
        Map<String, Object> map = new HashMap<>();
        String splits [] = null;
        //循环遍历推荐榜单列表
        for (MenuRecommend menuRecommends : recommendList) {
            movieName = new StringBuilder();
            //根据ids查询电影表中的数据，得到电影名
            List<String> movieMains = new ArrayList<>();
            if (menuRecommends.getMovieIds() != null) {
                splits = menuRecommends.getMovieIds().split(",");
                for (int i = 0; i < splits.length; i++) {
                    movieMains.add(splits[i]);
                }
                movieMainList = movieMainMapper.selectBatchIds(movieMains);
                //遍历得到的电影列表
                for (MovieMain movieMain : movieMainList) {
                    logger.info(movieMain.getName());
                    //得到电影列表中的电影名
                    movieName.append(movieMain.getName() + ",");
                }
                logger.info("未去除最后一个逗号:" + movieName);
                logger.info("去除最后一个逗号:" + movieName.substring(0, movieName.length() - 1));
                //放入推荐榜单实体类
                menuRecommend.setMovieName(movieName.substring(0, movieName.length() - 1));
                map.put(menuRecommends.getMovieIds(), menuRecommend.getMovieName());
            } else {
                menuRecommend.setMovieName("无推荐列表，请添加!");
                map.put(menuRecommends.getMovieIds(), menuRecommend.getMovieName());
            }
        }
        String movieNameMap = null;
        for (MenuRecommend menuRecommend1 : recommendList) {
            logger.info(menuRecommend1.getMovieIds());
            movieNameMap = (String) map.get(menuRecommend1.getMovieIds());
            menuRecommend1.setMovieName(movieNameMap);
        }
        logger.info(menuRecommend.getMovieName());
        return recommendList;
    }

    @Override
    public boolean update(Integer id, String movieIds) {
        MenuRecommend menuRecommend = baseMapper.selectById(id);
        MenuRecommend deleteMenu = new MenuRecommend();
        if (movieIds == null) {
            deleteMenu.setId(id);
            deleteMenu.setTitle(menuRecommend.getTitle());
            deleteMenu.setMovieIds(null);
        } else {
            deleteMenu.setId(id);
            deleteMenu.setTitle(menuRecommend.getTitle());
            deleteMenu.setMovieIds(movieIds);
        }
        return baseMapper.updateById(deleteMenu) > 0;
    }
}
