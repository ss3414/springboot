package com;

import com.exception.TestException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@SpringBootApplication
public class Exception {

    public static void main(String[] args) {
        SpringApplication.run(Exception.class, args);
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /* SpringBoot默认异常由BasicErrorController处理 */
    @RequestMapping("/test")
    public void test() {
        throw new RuntimeException("RuntimeException");
    }

    @RequestMapping("/test2")
    public void test2() {
        /* 处理多种异常 */
        try {
            throw new IOException();
        } catch (java.lang.Exception e) {
            throw new TestException(e.getMessage());
        }
    }

}
