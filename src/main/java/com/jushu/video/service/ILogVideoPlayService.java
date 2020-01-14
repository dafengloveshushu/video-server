package com.jushu.video.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.LogVideoPlay;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户点播日志表 服务类
 * </p>
 *
 * @author chen
 * @since 2020-01-14
 */
public interface ILogVideoPlayService extends IService<LogVideoPlay> {

    /**
     * 按条件查询所有播放记录并分页
     * @param page 分页工具类
     * @param paramFilter 查询条件类
     * @return  集合
     */
    List<LogVideoPlay> selectListPage(Page page, ParamFilter paramFilter);
}
