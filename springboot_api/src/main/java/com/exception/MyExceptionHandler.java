package com.exception;

import com.response.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(TestException.class)
    public ResponseResult<String> handleTestException(TestException e) {
        return ResponseResult.fail(e.getMessage());
    }

    /* 处理除TestException以外的其他异常 */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception e) {
        return ResponseResult.fail(e.getMessage());
    }

}
