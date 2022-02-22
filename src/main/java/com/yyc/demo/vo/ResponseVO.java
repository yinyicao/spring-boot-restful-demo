package com.yyc.demo.vo;

/**
 * 自定义的统一返回类型
 * @param <T>
 */
public class ResponseVO<T> {
    /**
     * 状态码，比如200代表响应成功
     */
    private Integer code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResponseVO() {
    }

    public ResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVO(ResponseCode responseCode, T data){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}