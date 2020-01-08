package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jushu.video.api.R;
import com.jushu.video.entity.GmAdmin;
import com.jushu.video.mapper.GmAdminMapper;
import com.jushu.video.service.IGmAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * <p>
 * 管理后台管理员用户表 服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-01-07
 */
@Service
public class GmAdminServiceImpl extends ServiceImpl<GmAdminMapper, GmAdmin> implements IGmAdminService {


    @Override
    public boolean save(GmAdmin entity) {
        entity.setAdminId(UUID.randomUUID().toString());
        entity.setCreateTime(new Date());
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public GmAdmin login(GmAdmin gmAdmin) {
        QueryWrapper<GmAdmin> gmAdminQueryWrapper = new QueryWrapper<>();
        gmAdminQueryWrapper.eq("account", gmAdmin.getAccount());
        gmAdminQueryWrapper.eq("password", gmAdmin.getPassword());
        GmAdmin gmAdmins = baseMapper.selectOne(gmAdminQueryWrapper);
        if(gmAdmins != null) {
            return gmAdmins;
        } else {
            return null;
        }
    }
}
