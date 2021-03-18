package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController /* 取代@Controller，默认返回JSON，不需要@ResponseBody */
@RequestMapping("")
public class IndexController {

    /**
     * @title 首页
     */
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

}
