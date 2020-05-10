package com.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * ①@Component标注组件Bean
 * ②@Controller/@Service/@Repository是细化组件
 * */
@Data
@Component
public class User {

    /* @Value使用SpEL表达式给属性/方法参数设置默认值 */
    @Value("${model.user.id}")
    private Integer id;

    @Value("${model.user.name}")
    private String name;

    //    @Value("${model.user.password}")
    private String password;

}
