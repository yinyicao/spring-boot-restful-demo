package com.yyc.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyc.demo.exception.APIException;
import com.yyc.demo.vo.ResponseCode;
import com.yyc.demo.vo.ResponseVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应处理（controller增强操作）
 * 也就是将我们的响应体统一封装为ResponseVO类型
 *
 * 在controller将数据进行返回前进行增强操作，
 * supports方法要返回为true才会执行beforeBodyWrite方法，
 * 所以如果有些情况不需要进行增强操作可以在supports方法里进行判断。
 * 对返回数据进行真正的操作还是在beforeBodyWrite方法中
 *
 * 注意：要加上需要扫描的包
 */
@RestControllerAdvice(basePackages = {"com.yyc.demo.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResponseVO那就没有必要进行额外的操作，返回false
        return !returnType.getGenericParameterType().equals(ResponseVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理，另一种处理方法可以使用WebMvcConfigurationSupport，参见./WebConfig.java
        // FIXME 不进行处理为啥会报类型转换异常：ResponseVO不能转为String？https://stackoverflow.com/questions/51828879/springboot-controller-return-data-through-responsebodyadvice-occur-an-error-cl/52200198#52200198?newreg=c566fe47bbf94e6ea14d503011797c5a
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResponseVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new ResponseVO<>(ResponseCode.SUCCESS,data));
            } catch (JsonProcessingException e) {
                throw new APIException("返回String类型错误");
            }
        }
        // 将原本的数据包装在ResponseVO里
        return new ResponseVO<>(ResponseCode.SUCCESS,data);
    }
}