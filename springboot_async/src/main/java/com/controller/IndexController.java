package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @RequestMapping("/")
    public Map index() {
        return new LinkedHashMap();
    }

}
