package com.demo;

import com.demo.annotation.LogAuto;
import com.demo.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@SpringBootApplication
public class Log {

    public static void main(String[] args) {
        SpringApplication.run(Log.class, args);
    }

    @RequestMapping("/")
    public Map index() {
        log.info("index");
        return new LinkedHashMap();
    }

    void son() {
        log.info("son");
    }

    /* 在调用链路开头添加MDC */
    @RequestMapping("/parent")
    public Map parent() {
        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("parent");
        son();
        MDC.clear();
        return new LinkedHashMap();
    }

    /* 自定义注解 */
    @LogAuto
    @RequestMapping("/parent2")
    public Map parent2() {
        log.info("parent2");
        son();
        return new LinkedHashMap();
    }

    /* 自定义返回体 */
    @LogAuto(outParamPrint = true)
    @RequestMapping("/parent3")
    public ResponseResult<Map> parent3() {
        log.info("parent3");
        son();
        return ResponseResult.success(new LinkedHashMap());
    }

}
