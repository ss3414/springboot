package com.util;

import com.annotation.Consume;
import com.service.TestService;
import com.config.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* 因为CustomProperties已被实例化，@Component自动扫描可以直接实例化StarterUtil */
@Component
public class StarterUtil {

    private CustomProperties customProperties;

    public StarterUtil(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    public void test() {
        System.out.println(customProperties.getFlag());
    }

    @Autowired
    private TestService testService;

    @Consume
    public void test2() {
        testService.test();
    }

}
