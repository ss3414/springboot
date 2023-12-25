package com.demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* 统计耗时 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Consume {

    String unit() default "s";

}
