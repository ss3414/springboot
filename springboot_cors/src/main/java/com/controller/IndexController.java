package com.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /* 跨域 */
    @GetMapping("/cors")
    public ModelAndView cors() {
        return new ModelAndView("/cors");
    }

    @GetMapping("/get")
    public Map get() {
        Map map = new HashMap();
        map.put("status", 1000);
        return map;
    }

    /************************************************************分割线************************************************************/

    /*
     * iframe获取父页面URL参数
     * ①father内嵌pretreat，pretreat根据father的URL参数跳转son
     * ②iframe只能通过JS获取父页面URL参数，但是不能直接写在son页面中，否则会死循环
     * */
    @GetMapping("/iframe")
    public ModelAndView iframe() {
        return new ModelAndView("/iframe/iframe");
    }

    /* 父页面 */
    @GetMapping("/father")
    public ModelAndView father() {
        return new ModelAndView("/iframe/father");
    }

    /* 预处理 */
    @GetMapping("/pretreat")
    public ModelAndView pretreat() {
        return new ModelAndView("/iframe/pretreat");
    }

    /* 子页面（预处理页面） */
    @GetMapping("/son")
    public ModelAndView son(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        ModelAndView view = new ModelAndView();
        view.addObject("currentPage", currentPage);
        view.addObject("pageSize", pageSize);
        view.setViewName("/iframe/son");
        return view;
    }

    /* 子页面（AJAX） */
    @GetMapping("/son2")
    public ModelAndView son2() {
        return new ModelAndView("/iframe/son2");
    }

    /* 子页面AJAX */
    @PostMapping("/sonAJAX")
    public Map sonAJAX(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Map map = new HashMap();
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        return map;
    }

}
