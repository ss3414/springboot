package com.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResult<T> {

    private T data;

    private String traceId;

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder()
                .data(data)
                .build();
    }

}
