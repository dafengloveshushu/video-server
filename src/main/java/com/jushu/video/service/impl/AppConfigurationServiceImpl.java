package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
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

    @Override
    public Page<AppConfiguration> appConfigurationListPage(Page page, ParamFilter paramFilter) {
        QueryWrapper queryWrapper = new QueryWrapper();
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean update(AppConfiguration appConfiguration) {
        return baseMapper.updateById(appConfiguration) > 0;
    }

    @Override
    public AppConfiguration getAppConfiguration(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean delete(String [] ids) {
        if (ids == null || ids.length <= 0) {
            return false;
        }
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.in("id", ids);
        return baseMapper.delete(wrapper) > 0;
    }
}
