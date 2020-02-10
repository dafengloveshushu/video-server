package com.jushu.video.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jushu.video.entity.LogVideoPlay;
import com.jushu.video.entity.MovieMain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
public interface MovieMainMapper extends BaseMapper<MovieMain> {

    /**
     * 查询当前所有的资源类型
     * @param wrapper
     * @return
     */
    @Select("<script> SELECT DISTINCT movieType FROM movie_main ${ew.customSqlSegment} </script>")
    List<String> selectMovieTypeList(@Param(Constants.WRAPPER) QueryWrapper wrapper);
}
