package com.demo.exception;

import com.demo.response.ResponseResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(TestException.class)
    public ResponseResult<String> handleTestException(TestException e) {
        return ResponseResult.fail(e.getMessage());
    }

    /* 处理@Valid的异常 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> handleValidException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return handleTestException(new TestException(errorMessages));
    }

    /* 处理除此之外的其他异常 */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception e) {
        return ResponseResult.fail(e.getMessage());
    }

}
