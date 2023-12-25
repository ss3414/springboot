package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@EnableScheduling
@SpringBootApplication
@MapperScan("com.**.mapper")
public class Task {

    public static void main(String[] args) {
        SpringApplication.run(Task.class, args);
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

}
