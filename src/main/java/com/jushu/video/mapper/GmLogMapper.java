package com.jushu.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jushu.video.entity.GmLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GmLogMapper extends BaseMapper<GmLog> {

    /**
     * 查询该表是否存在
     *
     * @param tableName 表名
     * @return 1为存在，0位不存在
     */
    @Select("select count(*) from information_schema.TABLES " +
            " where lcase(table_name) = #{tableName}")
    int existTable(String tableName);

    /**
     * 创建表
     *
     * @param tableName 表名
     * @return 1创建成功、0失败
     */
    @Update("CREATE TABLE `${tableName}` ( " +
            " `id` int(11) NOT NULL AUTO_INCREMENT, " +
            " `userName` varchar(255) NOT NULL COMMENT '用户名', " +
            " `loginIp` varchar(255) NOT NULL COMMENT '登录IP', " +
            "`loginTime` datetime(0) NOT NULL COMMENT '登录时间', " +
            " `userCreateTime` datetime(0) NOT NULL COMMENT '用户注册时间', " +
            " `userId` varchar(128) NOT NULL COMMENT '用户Id', " +
            " PRIMARY KEY (`id`) USING BTREE )")
    int createNewTable(@Param("tableName") String tableName);

    /**
     * 增加数据
     * @param tableName 表名
     * @param gmLog 日志表
     * @return  1为成功，0失败
     */
    @Insert("insert into `${tableName}` (`userName`,`loginIp`,`loginTime`,`userCreateTime`,`userId`) " +
            " values (#{log.userName}, #{log.loginIp}, #{log.loginTime}, #{log.userCreateTime}, #{log.userId} )")
    int insert(@Param("tableName") String tableName, @Param("log") GmLog gmLog);
}
