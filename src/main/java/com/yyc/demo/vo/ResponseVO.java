package com.yyc.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 自定义的统一返回类型
 * @param <T>
 */
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public ResponseVO(ResponseCode responseCode,T data){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }
}