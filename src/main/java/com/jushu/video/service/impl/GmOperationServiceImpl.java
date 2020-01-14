package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.GmAdmin;
import com.jushu.video.entity.GmOperation;
import com.jushu.video.mapper.GmOperationMapper;
import com.jushu.video.service.IGmOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<GmOperation> operationPageList(Page page, ParamFilter paramFilter) {
        QueryWrapper<GmOperation> queryWrapper = new QueryWrapper<>();
        if(paramFilter.getParam() != null) {
            Map<String, Object> map = new HashMap<>();
            map = paramFilter.getParam();
            String userName = (String) map.get("userName");
            if(userName != null) {
                queryWrapper.eq("user_name", userName);
            }
        }
        queryWrapper.orderByDesc("operation_time");
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean saveOperation(String method, String loginIp, String operation, int isSuccess, String remark, HttpSession session) {
        boolean flag = false;
        GmAdmin gmAdmin = (GmAdmin) session.getAttribute("user");
        if (gmAdmin == null) {
            return flag;
        }
        GmOperation gmOperation = new GmOperation();
        gmOperation.setUserId(gmAdmin.getAdminId());
        gmOperation.setUserName(gmAdmin.getAdminName());
        if(operation == null) {
            return flag;
        }
        gmOperation.setOperation(operation);
        if(method == null) {
            return flag;
        }
        gmOperation.setMethod(method);
        gmOperation.setOperationTime(new Date());
        if(loginIp == null) {
            return flag;
        }
        gmOperation.setLoginIp(loginIp);
        gmOperation.setIsSuccess(isSuccess);
        gmOperation.setRemark(remark);
        flag = baseMapper.insert(gmOperation) > 0;
        if(flag) {
            return flag;
        } else {
            return false;
        }
    }

}
