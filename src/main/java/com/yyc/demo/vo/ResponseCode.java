package com.yyc.demo.vo;

import lombok.Getter;

/**
 * 统一的响应码枚举类
 * @author yyc
 */
@Getter
public enum ResponseCode {

    SUCCESS(200, "操作成功"),

    FAILED(500, "响应失败"),

    API_CALL_FAILED(500,"API接口调用失败"),

    VALIDATE_FAILED(400, "参数校验失败"),

    ERROR(500, "未知错误");

    private Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }



}