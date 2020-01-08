package com.jushu.video.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理后台用户登录日志表
 * </p>
 *
 * @author chen
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GmLog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 用户创建时间
     */
    private Date userCreateTime;
    /**
     * 用户id
     */
    private String userId;
}
