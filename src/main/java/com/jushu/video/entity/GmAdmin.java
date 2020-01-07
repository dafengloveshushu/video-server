package com.jushu.video.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理后台管理员用户表
 * </p>
 *
 * @author chen
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GmAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(UUID)
     */
    private String adminId;

    /**
     * 管理员登录账号
     */
    private String account;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 管理员手机号
     */
    private String adminPhone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建操作账号ID
     */
    private String creatorId;

    /**
     * 创建操作账号
     */
    private String creatorName;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 删除操作账号ID
     */
    private String deleterId;

    /**
     * 删除操作账号
     */
    private String deleterName;

    /**
     * 删除标识
     */
    private Boolean isDeleted;

    /**
     * 最后操作时间
     */
    private Date lastUpdateTime;

    /**
     * 最后操作账号ID
     */
    private String lastUpdaterId;

    /**
     * 最后操作账号
     */
    private String lastUpdaterName;


}
