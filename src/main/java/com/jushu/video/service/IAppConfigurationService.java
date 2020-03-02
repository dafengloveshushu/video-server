package com.jushu.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.AppConfiguration;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author chen
 * @since 2020-02-28
 */
public interface IAppConfigurationService extends IService<AppConfiguration> {

    /**
     * 新增配置表
     * @param appConfiguration 配置实体
     * @return true或false
     */
    boolean insert(AppConfiguration appConfiguration);


    /**
     * 按条件查询并分页
     * @param page 分页工具类
     * @param paramFilter 条件工具类
     * @return  集合
     */
    Page<AppConfiguration> appConfigurationListPage(Page page, ParamFilter paramFilter);

    /**
     * 修改配置信息
     * @param appConfiguration 配置实体
     * @return true或false
     */
    boolean update(AppConfiguration appConfiguration);

    /**
     * 根据id查询一条配置信息
     * @param id id值
     * @return 配置信息
     */
    AppConfiguration getAppConfiguration(Integer id);

    /**
     * 根据id删除一条配置信息
     * @param ids id集合值
     * @return true或false
     */
    boolean delete(String [] ids);

}
