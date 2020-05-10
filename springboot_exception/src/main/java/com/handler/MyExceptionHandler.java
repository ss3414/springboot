package com.handler;

import com.exception.TestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(TestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) /* HTTP状态码：500错误 */
    public Map handleTestException(TestException e) {
        Map map = new HashMap();
        map.put("message", e.getMessage());
        return map;
    }

}
