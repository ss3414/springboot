package com.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {

    @NotNull(message = "id不能为Null")
    @Min(value = 1, message = "id>=1")
    @Max(value = 10, message = "id<=10")
    private Integer id;

    @NotEmpty(message = "name不能为空")
    private String name;

    private String password;

}
