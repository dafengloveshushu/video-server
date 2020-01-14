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
     * 创建表
     *
     * @param tableName 表名
     * @return 1创建成功、0失败
     */
    @Update("CREATE TABLE `${tableName}` ( " +
            " `id` int(11) NOT NULL AUTO_INCREMENT, " +
            " `createTime` datetime(0) NOT NULL COMMENT '点播时间', " +
            " `movieId` int(11) NOT NULL COMMENT '影片Id', " +
            "`movieName` varcaht(255) NOT NULL COMMENT '影片名称', " +
            " `partsId` int(11) NOT NULL COMMENT '剧集Id', " +
            " `partsTitle` varchar(255) NOT NULL COMMENT '剧集标题', " +
            " `videoUrl` varchar(1024) NOT NULL COMMENT '视频地址', " +
            " `imgUrl` varchar(1024) NOT NULL COMMENT '影片封面', " +
            " `watchTime` int(11) NOT NULL COMMENT '观看时长(s)', " +
            " `userId` int(11) NOT NULL COMMENT '用户Id(未登录为0)', " +
            " PRIMARY KEY (`id`) USING BTREE )")
    int createNewTable(@Param("tableName") String tableName);

    /**
     * 按条件查询所有播放记录并分页
     * @param page  分页工具类
     * @param wrapper 查询条件类
     * @return 集合
     */
    @Select("<script> SELECT lvp.movieId,lvp.movieName,mm.movieType,mm.tags,mm.year,lvp.watchTime,count(*) as counts FROM log_video_play lvp " +
            " left join movie_main mm on lvp.movieId = mm.id " +
            " ${ew.customSqlSegment} " +
//            " <where> " +
//            " 1 = 1 " +
//            " <if test=\"tags != null || tags != ''\"> and mm.tags like concat('%', #{tags}, '%') </if> " +
//            " <if test=\"movieName != null || movieName != ''\"> and lvp.movieName like concat('%', #{movieName}, '%') </if> " +
//            " </where> " +
//            " group by mm.id order by counts desc " +
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
            List<LogVideoPlay> selectListPage(Page page, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}
