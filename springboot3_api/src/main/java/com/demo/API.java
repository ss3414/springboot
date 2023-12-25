package com.demo;

import com.demo.exception.TestException;
import com.demo.model.User;
import com.demo.ratelimit.RateLimit;
import com.demo.response.ResponseResult;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@SpringBootApplication
public class API {

    public static void main(String[] args) {
        SpringApplication.run(API.class, args);
    }

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Index", HttpStatus.OK);
    }

    @RequestMapping("/success")
    public ResponseResult<String> success() {
        return ResponseResult.success();
    }

    @RequestMapping("/fail")
    public ResponseResult<String> fail() {
        return ResponseResult.fail();
    }

    @RequestMapping("/form")
    public ResponseResult<User> form(@Valid User user) {
        return ResponseResult.success(user);
    }

    /* SpringBoot默认异常由BasicErrorController处理 */
    @RequestMapping("/test")
    public void test() {
        throw new RuntimeException("RuntimeException");
    }

    @RequestMapping("/test2")
    public void test2() {
        throw new TestException("TestException");
    }

    @RateLimit(limit = 5)
    @RequestMapping("/limit")
    public ResponseResult<String> limit() {
        log.info("limit");
        return ResponseResult.success();
    }

    @RequestMapping("/nolimit")
    public ResponseResult<String> nolimit() {
        log.info("no limit");
        return ResponseResult.success();
    }

    @SneakyThrows
    @RequestMapping("/count")
    public ResponseResult<String> count() {
        int clientSize = 10;
        CountDownLatch countDown = new CountDownLatch(clientSize);
        ExecutorService threadPool = Executors.newFixedThreadPool(clientSize);
        IntStream.range(0, clientSize).forEach(i -> threadPool.submit(() -> {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject("http://127.0.0.1/limit", ResponseResult.class);
            countDown.countDown();
        }));
        countDown.await();
        threadPool.shutdown();
        return ResponseResult.success();
    }

}
