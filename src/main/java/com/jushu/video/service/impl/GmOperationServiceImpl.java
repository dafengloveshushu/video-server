package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.entity.GmOperation;
import com.jushu.video.mapper.GmOperationMapper;
import com.jushu.video.service.IGmOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2020-01-09
 */
@Service
public class GmOperationServiceImpl extends ServiceImpl<GmOperationMapper, GmOperation> implements IGmOperationService {

    @Override
    public Page<GmOperation> operationPageList(Page page, GmOperation gmOperation) {
        QueryWrapper<GmOperation> queryWrapper = new QueryWrapper<>();
        if(gmOperation.getUserName() != null) {
            queryWrapper.eq("user_name", gmOperation.getUserName());
        }
        return baseMapper.selectPage(page, queryWrapper);
    }

}
