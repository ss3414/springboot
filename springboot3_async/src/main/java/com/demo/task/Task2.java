package com.demo.task;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class Task2 {

    @Async
    @SneakyThrows
    public Future<String> task1() {
        Thread.sleep(new Random().nextInt(1000));
        return new CompletableFuture<>();
    }

    @Async
    @SneakyThrows
    public Future<String> task2() {
        Thread.sleep(new Random().nextInt(1000));
        return new CompletableFuture<>();
    }

}
