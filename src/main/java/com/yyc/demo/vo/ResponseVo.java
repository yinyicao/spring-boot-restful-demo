package com.yyc.demo.vo;

public class ResponseVo {
    private int code;   
    private String message;
    private Object data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private ResponseVo() {

    }

    public static ResponseVo success() {
        ResponseVo resultBean = new ResponseVo();
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static ResponseVo success(Object data) {
        ResponseVo resultBean = new ResponseVo();
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }
}