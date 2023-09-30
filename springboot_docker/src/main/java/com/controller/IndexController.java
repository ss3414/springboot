package com.controller;

import com.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public Map index() {
        Map map = new LinkedHashMap();
        map.put("status", 1000);
        map.put("user", userDao.findById(1L));
        return map;
    }

}
