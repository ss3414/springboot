package com.demo.util;

import com.demo.config.CustomProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/* 因为CustomProperties已被实例化，@Component自动扫描可以直接实例化StarterUtil */
@Slf4j
@Component
public class StarterUtil {

    private final CustomProperties customProperties;

    public StarterUtil(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    public void test() {
        log.info(customProperties.getFlag());
    }

}
