package com.demo;

import com.demo.client.Client;
import com.demo.event.DemoEvent;
import com.demo.task.Task1;
import com.demo.task.Task2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Slf4j
@RestController
@SpringBootApplication
public class Async {

    public static void main(String[] args) {
        SpringApplication.run(Async.class, args);
    }

    @RequestMapping("/")
    public Map index() {
        return new LinkedHashMap();
    }

    @Autowired
    private Task1 task1;

    /* 同步测试 */
    @RequestMapping("/test")
    public Map test() {
        task1.task1();
        task1.task2();
        return new LinkedHashMap();
    }

    @Autowired
    private Task2 task2;

    /* 异步测试 */
    @RequestMapping("/test2")
    public Map test2() {
        Future<String> future1 = task2.task1();
        Future<String> future2 = task2.task2();
        /* 没有返回值则无法使用isDone()函数 */
        while (true) { /* 轮询/事件循环 */
            if (future1.isDone() && future2.isDone()) {
                break;
            }
        }
        return new LinkedHashMap();
    }

    @Autowired
    private Client client;

    /* 异步请求 */
    @RequestMapping("/test3")
    public Map test3() {
        Future<String> future1 = client.httpclient("http://bbs.wuyou.net");
        Future<String> future2 = client.asyncHttpClient("http://bbs.wuyou.net");
        while (true) {
            if (future1.isDone() && future2.isDone()) {
                break;
            }
        }
        return new LinkedHashMap();
    }

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /* 同步（Listener不处理完，此处会阻塞） */
    @RequestMapping("/sync")
    public Map sync() {
        applicationEventPublisher.publishEvent(new DemoEvent("同步事件"));
        log.info("after event");
        return new LinkedHashMap();
    }

    /* 异步 */
    @RequestMapping("/async")
    public Map async() {
        applicationEventPublisher.publishEvent(new DemoEvent("异步事件"));
        log.info("after event");
        return new LinkedHashMap();
    }

}
