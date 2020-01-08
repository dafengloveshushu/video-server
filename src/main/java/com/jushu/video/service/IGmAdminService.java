package com.jushu.video.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.jushu.video.api.R;
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

    /**
     * 账号密码登录
     * @param gmAdmin 用户实体
     * @return  GmAdmin不为空成功，为空失败
     */
    GmAdmin login(GmAdmin gmAdmin);
}
