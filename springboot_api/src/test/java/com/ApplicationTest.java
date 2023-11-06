package com;

import com.response.ResponseResult;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ApplicationTest {

    @Test
    @SneakyThrows /* 等价于try catch代码 */ public void test() {
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
    }

}
