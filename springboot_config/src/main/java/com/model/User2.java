package com.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * ①通过@PropertySource指定配置
 * ②@ConfigurationProperties替代@Value
 * ③@ConfigurationProperties+@Validated数据验证
 * */
@Data
@Validated
@Component
@ConfigurationProperties(prefix = "model.user")
@PropertySource("classpath:application.properties")
public class User2 {

    @NotNull
    private Integer id;

    @NotEmpty
    private String name;

    private String password;

}
