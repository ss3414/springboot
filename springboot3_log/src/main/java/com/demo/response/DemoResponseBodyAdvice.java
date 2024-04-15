package com.demo.response;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice
public class DemoResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /* 根据returnType或converterType决定修改哪些方法 */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        /* 返回值是否为ResponseResult */
        return ResponseResult.class.isAssignableFrom(returnType.getParameterType());
    }

    /* 修改返回体 */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("beforeBodyWrite");
        String traceId = MDC.get("traceId");
        ResponseResult result = (ResponseResult) body;
        result.setTraceId(traceId);
        MDC.clear();
        return result;
    }

}
