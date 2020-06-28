package com.util.test;

import com.annotation.Consume;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Task1 {

    private static Random random = new Random();

    @Consume(unit = "ms")
    public void task1() throws InterruptedException {
        Thread.sleep(random.nextInt(1000));
    }

    @Consume(unit = "ms")
    public void task2() throws InterruptedException {
        Thread.sleep(random.nextInt(1000));
    }

}
