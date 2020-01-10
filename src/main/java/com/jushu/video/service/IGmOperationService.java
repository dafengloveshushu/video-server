package com.jushu.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.GmOperation;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
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


    /**
     * 新增一条操作记录
     * @param method    操作方法
     * @param loginIp   当前ip
     * @return  ture为成功，false失败
     */
    boolean saveOperation(String method, String loginIp, String operation, int isSuccess, String remark, HttpSession session);
}
