package com.demo.initial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class Runner2 implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Runner2");
    }

}
