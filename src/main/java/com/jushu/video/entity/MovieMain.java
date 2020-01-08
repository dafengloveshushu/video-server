package com.jushu.video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
public class MovieMain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 演员列表，多个用|隔开
     */
    private String actors;

    /**
     * 国家
     */
    private String country;

    /**
     * 导演，多个用|隔开
     */
    private String director;

    /**
     * 封面图
     */
    @TableField("imgUrl")
    private String imgUrl;

    /**
     * 是否完结
     */
    @TableField("isOver")
    private Boolean isOver;

    /**
     * 影片来源
     */
    @TableField("movieSource")
    private String movieSource;

    /**
     * 影片类型
     */
    @TableField("movieType")
    private String movieType;

    /**
     * 影片名称
     */
    private String name;

    /**
     * 爬数据时候用的不用管
     */
    @TableField("pageId")
    private String pageId;

    /**
     * 详情页
     */
    @TableField("pageUrl")
    private String pageUrl;

    /**
     * 描述
     */
    private String reviews;

    /**
     * 评分
     */
    private String score;

    /**
     * 更新状态描述
     */
    @TableField("statusText")
    private String statusText;

    /**
     * 标签，多个用|隔开
     */
    private String tags;

    /**
     * 年份
     */
    private String year;

    /**
     * 爬数据时候用的不用管
     */
    @TableField("lastRefreshDate")
    private LocalDate lastRefreshDate;


}
