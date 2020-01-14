package com.jushu.video.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户点播日志表
 * </p>
 *
 * @author chen
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("log_video_play")
public class LogVideoPlay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 点播时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 影片Id
     */
    @TableField("movieId")
    private Integer movieId;

    /**
     * 影片名称
     */
    @TableField("movieName")
    private String movieName;

    /**
     * 剧集Id
     */
    @TableField("partsId")
    private Integer partsId;

    /**
     * 剧集标题
     */
    @TableField("partsTitle")
    private String partsTitle;

    /**
     * 视频地址
     */
    @TableField("videoUrl")
    private String videoUrl;

    /**
     * 影片封面
     */
    @TableField("imgUrl")
    private String imgUrl;

    /**
     * 观看时长(s)
     */
    @TableField("watchTime")
    private Integer watchTime;

    /**
     * 用户Id(未登录为0)
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 连表查询字段
     */
    @TableField(exist = false, value = "movieType")
    private String movieType;

    /**
     * 连表查询字段
     */
    @TableField(exist = false, value = "tags")
    private String tags;

    /**
     * 连表查询字段
     */
    @TableField(exist = false, value = "year")
    private String year;

    /**
     * 连表查询字段
     */
    @TableField(exist = false, value = "counts")
    private Integer counts;
}
