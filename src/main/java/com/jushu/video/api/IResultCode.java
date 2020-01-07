package com.jushu.video.api;

import java.io.Serializable;

/**
 * @author 大奉
 * @create 2019-11-19 15:16
 * @blame ResultCode
 */
public interface IResultCode extends Serializable {
    /**
     * 返回的消息
     * @return 消息
     */
    String getMessage();

    /**
     * 返回的状态码
     * @return 状态码
     */
    int getCode();
}
