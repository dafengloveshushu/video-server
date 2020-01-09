package com.jushu.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


    Page<GmOperation> operationPageList(Page page, GmOperation gmOperation);
}
