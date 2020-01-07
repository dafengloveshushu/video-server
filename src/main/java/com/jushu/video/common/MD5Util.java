package com.jushu.video.common;


import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author 大奉
 * MD5 工具类
 * @create 2019-11-21 9:30
 */
public class MD5Util {


    /**
     * 生成MD5编码
     * @param data      要编码的字符串
     * @param time      加密次数
     * @return     加密后的字符串
     */
    public static String md5(String data, int time) {
        try {
            byte[] bytes = data == null ? new byte[0] : data.getBytes("utf-8");
            while (time --> 1) {
                bytes = DigestUtils.md5Digest(bytes);
            }
            return DigestUtils.md5DigestAsHex(bytes).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5(String data) {
        return md5(data,1);
    }

}
