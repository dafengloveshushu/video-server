package com.jushu.video.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.api.ParamFilter;
import com.jushu.video.entity.LogVideoPlay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 用户点播日志表 Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2020-01-14
 */
public interface LogVideoPlayMapper extends BaseMapper<LogVideoPlay> {

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
     * 按条件查询所有播放记录并分页
     * @param page  分页工具类
     * @param wrapper 查询条件类
     * @return 集合
     */
    @Select("<script> SELECT lvp.movieId,lvp.movieName,mm.movieType,mm.tags,mm.year,lvp.watchTime,count(*) as counts FROM ${tableName} lvp" +
            " left join movie_main mm on lvp.movieId = mm.id " +
            " ${ew.customSqlSegment} " +
            "</script>")
            @Results({
                    @Result(column = "movieId", property = "movieId"),
                    @Result(column = "movieName", property = "movieName"),
                    @Result(column = "movieType", property = "movieType"),
                    @Result(column = "tags", property = "tags"),
                    @Result(column = "year", property = "year"),
                    @Result(column = "watchTime", property = "watchTime"),
                    @Result(column = "counts", property = "counts")
            })
            List<LogVideoPlay> selectListPage(Page page, @Param("tableName") String tableName, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}
