package com.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    private String status;

    private String message;

    private long timestamp;

    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder()
                .status(ResponseStatus.SUCCESS.getCode())
                .message(ResponseStatus.SUCCESS.getDescription())
                .timestamp(System.currentTimeMillis())
                .data(data)
                .build();
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> fail(T data) {
        return ResponseResult.<T>builder()
                .status(ResponseStatus.FAIL.getCode())
                .message(ResponseStatus.FAIL.getDescription())
                .timestamp(System.currentTimeMillis())
                .data(data)
                .build();
    }

    public static <T> ResponseResult<T> fail() {
        return fail(null);
    }

}
