package com.yyc.demo.exception;

import com.yyc.demo.vo.ResponseCode;

/**
 * 自定义API异常
 * 只要getter方法，无需setter
 */
public class ApiException extends RuntimeException {
    private int code;
    private String msg;

    public ApiException() {
        this(ResponseCode.ERROR);
    }

    public ApiException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}