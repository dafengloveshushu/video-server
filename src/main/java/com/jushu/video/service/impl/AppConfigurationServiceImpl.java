package com.jushu.video.service.impl;

import com.jushu.video.entity.AppConfiguration;
import com.jushu.video.mapper.AppConfigurationMapper;
import com.jushu.video.service.IAppConfigurationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-02-28
 */
@Service
public class AppConfigurationServiceImpl extends ServiceImpl<AppConfigurationMapper, AppConfiguration> implements IAppConfigurationService {

    @Override
    public boolean insert(AppConfiguration appConfiguration) {
        appConfiguration.setConfigKey("播放时间");
        appConfiguration.setRemark("添加vip电影播放时间段!");
        return baseMapper.insert(appConfiguration) > 0;
    }
}
