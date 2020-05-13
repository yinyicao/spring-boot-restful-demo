package com.yyc.demo.config;

import com.yyc.demo.exception.ApiException;
import com.yyc.demo.vo.ResponseCode;
import com.yyc.demo.vo.ResponseVO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 * @author yyc
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 参数校验失败异常处理
     * 注意：Controller层方法参数接收为一个对象时，可以加@RequestBody注解也可以不加。
     * 1.但是不加就代表参数使用 url 参数，或者表单的形式传输，参数校验失败会抛出BindException
     * 2.加了就代表参数使用body体（JSON格式）的形式传输，参数校验失败抛出MethodArgumentNotValidException
     * @RequestBody @Valid
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回，返回自定义实体类型
        return new ResponseVO<>(ResponseCode.VALIDATE_FAILED,objectError.getDefaultMessage());
    }

    /**
     * 自定义异常ApiException处理
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public ResponseVO<String> APIExceptionHandler(ApiException e) {
        // 返回自定义实体类型
        return new ResponseVO<>(ResponseCode.API_CALL_FAILED,e.getMsg());
    }

    /**
     * EmptyResultDataAccessException处理
     * 删除数据库中不存在的资源会抛出这个异常
     * @param e
     * @return
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseVO<String> EmptyResultDataAccessExceptionHandler(EmptyResultDataAccessException e){
        return new ResponseVO<>(ResponseCode.SUCCESS,null);
    }
}