package com.demo.task;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Task1 {

    @SneakyThrows
    public void task1() {
        Thread.sleep(new Random().nextInt(1000));
    }

    @SneakyThrows
    public void task2() {
        Thread.sleep(new Random().nextInt(1000));
    }

}
