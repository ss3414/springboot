package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(TestException.class)
    @ResponseStatus(HttpStatus.OK) /* HTTP状态码 */
    public Map handleTestException(TestException e) {
        Map map = new LinkedHashMap();
        map.put("message", e.getMessage());
        return map;
    }

    /* 处理除TestException以外的其他异常 */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Map handleException(Exception e) {
        Map map = new LinkedHashMap();
        map.put("message", e.getMessage());
        return map;
    }

}
