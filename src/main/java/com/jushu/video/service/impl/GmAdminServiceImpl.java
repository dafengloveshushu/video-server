package com.jushu.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jushu.video.common.IpUtil;
import com.jushu.video.entity.GmAdmin;
import com.jushu.video.entity.GmLog;
import com.jushu.video.mapper.GmAdminMapper;
import com.jushu.video.mapper.GmLogMapper;
import com.jushu.video.service.IGmAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
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

    private Logger logger = LoggerFactory.getLogger(GmAdminServiceImpl.class);


    @Autowired
    private GmLogMapper gmLogMapper;

//    @Autowired
//    private GmOperationMapper gmOperationMapper;

    @Override
    public boolean save(GmAdmin entity) {
        entity.setAdminId(UUID.randomUUID().toString());
        entity.setCreateTime(new Date());
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public GmAdmin login(GmAdmin gmAdmin, HttpServletRequest  request) {
        logger.info("用户登录开始---");
        //获取当前方法名，供打点管理员操作记录表使用
        //String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        QueryWrapper<GmAdmin> gmAdminQueryWrapper = new QueryWrapper<>();
        gmAdminQueryWrapper.eq("account", gmAdmin.getAccount());
        gmAdminQueryWrapper.eq("password", gmAdmin.getPassword());
        GmAdmin gmAdmins = baseMapper.selectOne(gmAdminQueryWrapper);
        logger.info("用户信息:" + gmAdmins.getAdminName());
        if(gmAdmins != null) {
            GmLog gmLog = new GmLog();
            Calendar calendar = Calendar.getInstance();
            //获取当前年份
            int year = calendar.get(Calendar.YEAR);
            //获取当前月份
            int month = calendar.get(Calendar.MONTH) + 1;
            //当前表名
            String tableName = null;
            //如果当前月份长度为1 则在前面加上0
            if(String.valueOf(month).length() == 1) {
                tableName = "gm_log_" + year + 0 + month;
            } else {
                tableName = "gm_log_" + year + month;
            }
            int isTable = gmLogMapper.existTable(tableName);
            logger.info("当前日志表:" + tableName);
            //如果该表找不到，则对该表进行新建
            if(isTable == 0) {
                logger.info("当前不存在该表---");
                int newTable = gmLogMapper.createNewTable(tableName);
                //newTable 等于0 代表新建成功
                if(newTable == 0) {
                    logger.info("创建该表");
                    gmLog.setUserName(gmAdmins.getAdminName());
                    gmLog.setLoginIp(IpUtil.getIpAddr(request));
                    gmLog.setLoginTime(new Date());
                    gmLog.setUserCreateTime(gmAdmins.getCreateTime());
                    gmLog.setUserId(gmAdmins.getAdminId());
                    int insert = gmLogMapper.insert(tableName, gmLog);
                    if(insert > 0) {
                        logger.info("日志添加成功!");
                        return gmAdmins;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                gmLog.setUserName(gmAdmins.getAdminName());
                gmLog.setLoginIp(IpUtil.getIpAddr(request));
                gmLog.setLoginTime(new Date());
                gmLog.setUserCreateTime(gmAdmins.getCreateTime());
                gmLog.setUserId(gmAdmins.getAdminId());
                int insert = gmLogMapper.insert(tableName, gmLog);
                if(insert > 0) {
                    logger.info("该表为已存在的表,日志添加成功!");
                    return gmAdmins;
                } else {
                    return null;
                }
            }
        } else {
            logger.info("登录失败,用户不存在!");
            return null;
        }
    }
}
