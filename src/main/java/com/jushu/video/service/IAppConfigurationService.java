package com.jushu.video.service;

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

}
