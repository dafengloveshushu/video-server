package com.jushu.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.GmOperation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2020-01-09
 */
public interface IGmOperationService extends IService<GmOperation> {

    /**
     * 按条件查询并分页
     * @param page 分页工具类
     * @param paramFilter 条件工具类
     * @return  集合
     */
    Page<GmOperation> operationPageList(Page page, ParamFilter paramFilter);
}
