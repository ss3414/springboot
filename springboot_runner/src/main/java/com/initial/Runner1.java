package com.initial;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class Runner1 implements ApplicationRunner {

    /* 在所有Spring Bean初始化后，在Application运行前运行 */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Runner1.run()");
    }

}
