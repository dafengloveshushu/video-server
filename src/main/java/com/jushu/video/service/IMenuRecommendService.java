package com.jushu.video.service;

import com.jushu.video.entity.MenuRecommend;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户端-推荐列表配置表 服务类
 * </p>
 *
 * @author chen
 * @since 2020-02-07
 */
public interface IMenuRecommendService extends IService<MenuRecommend> {

    /**
     * 查询所有推荐榜单列表
     * @return 推荐榜单集合
     */
    List<MenuRecommend> recommendList();

    /**
     * 根据id修改榜单列表
     * @param id 参数
     * @param movieIds 电影id
     * @return true为成功，false为失败
     */
    boolean update(@Param("id") Integer id, @Param("movieIds") String movieIds);

    /**
     * 根据id查找推荐榜单列表
     * @param id 参数id
     * @return 返回实体数据
     */
    MenuRecommend getMenuRecommendById(@Param("id") Integer id);
}
