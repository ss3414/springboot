package com.test;

import com.annotation.Consume;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Component
public class Task2 {

    private static Random random = new Random();

    @Async
    @Consume(unit = "ms")
    public Future<String> task1() throws InterruptedException {
        Thread.sleep(random.nextInt(1000));
        return new AsyncResult<>("");
    }

    @Async
    @Consume(unit = "ms")
    public Future<String> task2() throws InterruptedException {
        Thread.sleep(random.nextInt(1000));
        return new AsyncResult<>("");
    }

}
