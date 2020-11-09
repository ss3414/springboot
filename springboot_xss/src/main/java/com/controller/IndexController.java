package com.controller;

import com.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /* form()：getParameter（user）>getParameterNames（id/name/pwd）>getParameterValues（依次获取id/name/pwd值） */
    @PostMapping("/form")
    public ModelAndView form(User user) {
        ModelAndView view = new ModelAndView();
        view.addObject("user", user);
        view.setViewName("/user");
        return view;
    }

    /* para()：getParameterValues（获取test值） */
    @RequestMapping("/para")
    public Map para(String test) {
        System.out.println(test);

        Map map = new HashMap();
        map.put("status", 1000);
        return map;
    }

    /* para2()：getParameterValues（依次获取test/test2值） */
    @RequestMapping("/para2")
    public Map para2(String test1, String test2) {
        System.out.println(test1);
        System.out.println(test2);

        Map map = new HashMap();
        map.put("status", 1000);
        return map;
    }

}
