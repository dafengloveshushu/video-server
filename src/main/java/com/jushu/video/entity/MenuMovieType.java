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
 * 客户端-筛选菜单配置表
 * </p>
 *
 * @author chen
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuMovieType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * Tab标题
     */
    private String title;

    /**
     * 影片类型
     */
    @TableField("movieTypes")
    private String movieTypes;

    /**
     * 筛选标签
     */
    @TableField("menuTags")
    private String menuTags;

    /**
     * 筛选地区
     */
    @TableField("menuCountries")
    private String menuCountries;

    /**
     * 筛选年份
     */
    @TableField("menuYears")
    private String menuYears;


}
