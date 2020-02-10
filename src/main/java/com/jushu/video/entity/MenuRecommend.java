package com.jushu.video.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户端-推荐列表配置表
 * </p>
 *
 * @author chen
 * @since 2020-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuRecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 推荐标题
     */
    private String title;

    /**
     * 影片列表(6个Id)
     */
    @TableField(value = "movieIds", updateStrategy = FieldStrategy.IGNORED)
    private String movieIds;


    /**
     * 电影名字，查询字段
     */
    @TableField(exist = false, value = "movieName")
    private String movieName;


}
