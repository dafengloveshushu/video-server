package com.jushu.video.service;

import com.jushu.video.entity.GmAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理后台管理员用户表 服务类
 * </p>
 *
 * @author chen
 * @since 2020-01-07
 */
public interface IGmAdminService extends IService<GmAdmin> {

    /**
     * 新增用户
     * @param entity 用户实体
     * @return 成功为true 失败为false
     */
    @Override
    boolean save(GmAdmin entity);
}
