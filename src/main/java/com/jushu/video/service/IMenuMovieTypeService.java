package com.jushu.video.service;

import com.jushu.video.entity.MenuMovieType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jushu.video.entity.MenuRecommend;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 客户端-筛选菜单配置表 服务类
 * </p>
 *
 * @author chen
 * @since 2020-02-13
 */
public interface IMenuMovieTypeService extends IService<MenuMovieType> {
    /**
     * 查询筛选配置
     * @return 筛选配置
     */
    List<MenuMovieType> recommendList();

    Boolean create(@Param("menuMovieType") MenuMovieType menuMovieType);

    Boolean delete(String[] ids);
}
