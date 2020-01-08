package com.jushu.video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MovieParts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * m3u8播放列表数据
     */
    private String data;

    /**
     * 数据类型
     */
    @TableField("dataType")
    private String dataType;

    /**
     * 对应movie_main的id
     */
    @TableField("movieId")
    private Integer movieId;

    /**
     * 视频来源
     */
    @TableField("movieSource")
    private String movieSource;

    /**
     * 不用管
     */
    @TableField("pageId")
    private String pageId;

    /**
     * 播放页地址
     */
    @TableField("pageUrl")
    private String pageUrl;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频源地址
     */
    @TableField("videoUrl")
    private String videoUrl;

    /**
     * 视频源ID，对应datasource_const的id
     */
    @TableField("dataSourceId")
    private Integer dataSourceId;

    /**
     * 影片名称
     */
    @TableField("movieName")
    private String movieName;


}
