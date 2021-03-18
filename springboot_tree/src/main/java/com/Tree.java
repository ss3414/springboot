package com;

import cn.yueshutong.springprojecttree.config.annotation.EnableProjectTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProjectTree("execution(* com.demo..*(..))")
public class Tree {

    public static void main(String[] args) {
        SpringApplication.run(Tree.class, args);
    }

}
