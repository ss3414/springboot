package com.controller;

import com.exception.TestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /* SpringBoot默认异常由BasicErrorController处理 */
    @GetMapping("/test")
    public void test() {
        throw new RuntimeException("exception test");
    }

    @GetMapping("/test2")
    public void test2() {
        throw new TestException();
    }

}
