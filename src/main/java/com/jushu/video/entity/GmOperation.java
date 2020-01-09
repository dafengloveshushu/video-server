package com.jushu.video.entity;

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
 * 
 * </p>
 *
 * @author chen
 * @since 2020-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GmOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员ID
     */
    @TableField("userId")
    private String userId;

    /**
     * 管理员名称
     */
    private String userName;

    /**
     * 管理员操作
     */
    private String operation;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 是否成功 (0成功、1失败)
     */
    private Integer isSuccess;

    /**
     * 备注
     */
    private String remark;


}
