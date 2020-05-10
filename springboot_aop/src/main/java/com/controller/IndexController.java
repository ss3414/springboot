package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public Map index() throws InterruptedException {
        Thread.sleep(1000);

        Map map = new HashMap();
        map.put("status", 1000);
        return map;
    }

}
