package com.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
