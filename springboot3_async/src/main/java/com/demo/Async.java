package com.demo;

import com.demo.async.client.Client;
import com.demo.async.service.ProductService;
import com.demo.async.task.Task1;
import com.demo.async.task.Task2;
import com.demo.model.Product;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@EnableAsync
@RestController
@SpringBootApplication
@MapperScan("com.**.mapper")
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
    @SneakyThrows
    @RequestMapping("/test")
    public Map test() {
        task1.task1();
        task1.task2();
        return new LinkedHashMap();
    }

    @Autowired
    private Task2 task2;

    /* 异步测试 */
    @SneakyThrows
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
    @SneakyThrows
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
    private ProductService productService;

    /* 异步测试 */
    @RequestMapping("/test4")
    public Map test4() {
        Future<List<Product>> future1 = productService.selectAll();
        Future<List<Product>> future2 = productService.selectAll();
        while (true) {
            if (future1.isDone() && future2.isDone()) {
                break;
            }
        }
        return new LinkedHashMap();
    }

}
