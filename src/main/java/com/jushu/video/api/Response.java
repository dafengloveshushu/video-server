package com.jushu.video.api;

/**
 * @author 大奉
 * @date 2020/1/8 12:21
 * @blame 大奉
 */
public class Response implements ResponseCode {
    private Object data;
    //默认为0表示响应正常
    private int code = 0;

    private String msg;

    public Response() {
    }

    public Response(Object data) {
        this.data = data;
    }

    public Response(String msg) {
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
